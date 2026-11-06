package pd11.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd11.model.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesExporter {
    public void exportToJson(Path outputPath, List<Product> products, SalesService salesService) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Map<String, Object> reportData = new HashMap<>();
        reportData.put("totalRevenue", salesService.calculateTotalRevenue(products));
        reportData.put("top3Products", salesService.getTop3ProductsByRevenue(products));
        reportData.put("revenueByCategory", salesService.getRevenueByCategory(products));
        reportData.put("averageUnitPriceByCategory", salesService.getAverageUnitPriceByCategory(products));
        reportData.put("dayWithHighestSales", salesService.getDayWithHighestSales(products));

        mapper.writeValue(outputPath.toFile(), reportData);
    }

    public void exportSummaryToCsv(Path outputPath, Map<String, Double> revenueByCategory, Map<String, Double> avgPriceByCategory) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("category,total_revenue,average_unit_price");
            writer.newLine();

            for (String category : revenueByCategory.keySet()) {
                double revenue = revenueByCategory.get(category);
                double avgPrice = avgPriceByCategory.getOrDefault(category, 0.0);

                writer.write(String.format("%s,%.2f,%.2f", category, revenue, avgPrice));
                writer.newLine();
            }
        }
    }
}