package pd11.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Report {
    private double totalRevenue;
    private List<Product> top3Products;
    private Map<String, Double> revenueByCategory;
    private Map<String, Double> averageUnitPriceByCategory;
    private LocalDate dayWithHighestSales;

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public List<Product> getTop3Products() {
        return top3Products;
    }

    public Map<String, Double> getRevenueByCategory() {
        return revenueByCategory;
    }

    public Map<String, Double> getAverageUnitPriceByCategory() {
        return averageUnitPriceByCategory;
    }

    public LocalDate getDayWithHighestSales() {
        return dayWithHighestSales;
    }
}
