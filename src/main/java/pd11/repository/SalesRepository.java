package pd11.repository;

import pd11.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SalesRepository {
    double calculateTotalRevenue(List<Product> products);

    List<Product> getTop3ProductsByRevenue(List<Product> products);

    Map<String, Double> getRevenueByCategory(List<Product> products);

    Map<String, Double> getAverageUnitPriceByCategory(List<Product> products);

    LocalDate getDayWithHighestSales(List<Product> products);

}
