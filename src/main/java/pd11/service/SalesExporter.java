package pd11.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd11.model.Sale;
import pd11.model.Report;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class SalesExporter {
    public void exportToJson(Path outputPath, Report report) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(outputPath.toFile(), report);
    }

    public void exportSummaryToCsv(Path outputPath, Map<String, BigDecimal> revenueByCategory, Map<String, BigDecimal> avgPriceByCategory) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("category,total_revenue,average_unit_price");
            writer.newLine();

            for (String category : revenueByCategory.keySet()) {
                BigDecimal revenue = revenueByCategory.get(category);
                BigDecimal avgPrice = avgPriceByCategory.getOrDefault(category, BigDecimal.ZERO);

                writer.write(String.format("%s,%.2f,%.2f", category, revenue, avgPrice));
                writer.newLine();
            }
        }
    }

    public Report createReport(SalesService salesService, List<Sale> products) {
        return new Report(
            salesService.calculateTotalRevenue(products),
            salesService.getRevenueByCategory(products),
            salesService.getTop3ProductsByRevenue(products),
            salesService.getDayWithHighestSales(products),
            salesService.getAverageUnitPriceByCategory(products)
        );
    }
}