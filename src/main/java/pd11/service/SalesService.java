package pd11.service;

import pd11.model.Sale;
import pd11.interfaces.SalesInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalesService implements SalesInterface {

    private BigDecimal calculateRevenue(Sale sale) {
        return sale.getUnitPrice()
            .multiply(BigDecimal.valueOf(sale.getQuantity()));
    }

    private <K> Map<K, BigDecimal> calculateRevenueBy(
        List<Sale> products,
        Function<Sale, K> classifier) {

        return products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                classifier,
                Collectors.reducing(
                    BigDecimal.ZERO,
                    this::calculateRevenue,
                    BigDecimal::add
                )
            ));
    }

    private <K> Map<K, BigDecimal> sortByRevenue(Map<K, BigDecimal> revenue) {
        return revenue.entrySet().stream()
            .sorted(Map.Entry.<K, BigDecimal>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }

    @Override
    public BigDecimal calculateTotalRevenue(List<Sale> products) {
        return products.stream()
            .filter(Objects::nonNull)
            .map(this::calculateRevenue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Sale> getTop3ProductsByRevenue(List<Sale> products) {
        Map<String, BigDecimal> revenue =
            calculateRevenueBy(products, Sale::getProductId);

        return sortByRevenue(revenue).keySet().stream()
            .limit(3)
            .map(id -> products.stream()
                .filter(Objects::nonNull)
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElse(null))
            .filter(Objects::nonNull)
            .toList();
    }

    @Override
    public Map<String, BigDecimal> getRevenueByCategory(List<Sale> products) {
        return sortByRevenue(
            calculateRevenueBy(products, Sale::getCategory)
        );
    }

    @Override
    public Map<String, BigDecimal> getAverageUnitPriceByCategory(
        List<Sale> products) {

        return products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Sale::getCategory,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    sales -> sales.stream()
                        .map(Sale::getUnitPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(
                            BigDecimal.valueOf(sales.size()),
                            4,
                            RoundingMode.HALF_UP
                        )
                )
            ));
    }

    @Override
    public LocalDate getDayWithHighestSales(List<Sale> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }

        return calculateRevenueBy(products, Sale::getDate)
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}