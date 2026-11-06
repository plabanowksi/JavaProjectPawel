package pd11.service;

import pd11.model.Product;
import pd11.repository.SalesRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SalesService implements SalesRepository {

    @Override
    public double calculateTotalRevenue(List<Product> products) {
        return products.stream()
            .filter(Objects::nonNull)
            .mapToDouble(p -> p.getQuantity() * p.getUnitPrice())
            .sum();
    }

    @Override
    public List<Product> getTop3ProductsByRevenue(List<Product> products) {

        Map<String, Double> revenueByProducts = products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Product::getProductId, Collectors.summingDouble(product -> product.getQuantity() * product.getUnitPrice())
            ));

        return revenueByProducts.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(3)
            .map(entry -> {
                String productId = entry.getKey();
                return products.stream()
                    .filter(p -> p.getProductId().equals(productId))
                    .findFirst()
                    .orElse(null);
            })
            .filter(Objects::nonNull)
            .toList();
    }

    @Override
    public Map<String, Double> getRevenueByCategory(List<Product> products) {
        Map<String, Double> revenueByCategory = products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Product::getCategory, Collectors.summingDouble(product -> product.getQuantity() * product.getUnitPrice())
            ));

        return revenueByCategory.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }

    @Override
    public Map<String, Double> getAverageUnitPriceByCategory(List<Product> products) {
        return products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Product::getCategory, Collectors.averagingDouble(Product::getUnitPrice)
            ));
    }

    @Override
    public LocalDate getDayWithHighestSales(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }
        Map<LocalDate, Double> revenueByDate = products.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Product::getDate,
                Collectors.summingDouble(product -> product.getQuantity() * product.getUnitPrice())
            ));

        return revenueByDate.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}