package pd11.interfaces;

import pd11.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SalesInterface {
    BigDecimal calculateTotalRevenue(List<Sale> products);

    List<Sale> getTop3ProductsByRevenue(List<Sale> products);

    Map<String, BigDecimal> getRevenueByCategory(List<Sale> products);

    Map<String, BigDecimal> getAverageUnitPriceByCategory(List<Sale> products);

    LocalDate getDayWithHighestSales(List<Sale> products);

}
