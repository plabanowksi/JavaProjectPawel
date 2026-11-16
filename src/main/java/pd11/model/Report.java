package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record Report(
    BigDecimal totalRevenue,
    Map<String, BigDecimal> revenueByCategory,
    List<Sale> top3Products,
    LocalDate dayWithHighestSales,
    Map<String, BigDecimal> averageUnitPriceByCategory
) {
}