package pd11;

import pd11.model.Product;
import pd11.service.CsvSalesLoader;
import pd11.service.SalesExporter;
import pd11.service.SalesService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final Path DIR = Path.of("src/main/resources");

    public static void main(String[] args) throws IOException {
        CsvSalesLoader loader = new CsvSalesLoader();
        Path csvFile =DIR.resolve("sales.csv");
        List<Product> products = loader.loadSales(csvFile);

        System.out.println("Initialize: " + products.size() + "records");
        SalesService salesService = new SalesService();
        double totalRevenue = salesService.calculateTotalRevenue(products);
        System.out.printf("Total Revenue: %.2f PLN\n", totalRevenue);
        List<Product> top3 = salesService.getTop3ProductsByRevenue(products);
        System.out.println("Top 3 products by Revenue:");
        top3.forEach(product -> System.out.println("   - " + product.getProductName() + " (ID: " + product.getProductId() + ")"));
        System.out.println("Revenue by Category:");
        salesService.getRevenueByCategory(products)
            .forEach((category, revenue) -> System.out.printf("   - %s: %.2f PLN\n", category, revenue));

        System.out.println("Average unit price by Category:");
        salesService.getAverageUnitPriceByCategory(products)
            .forEach((category, avgPrice) -> System.out.printf("   - %s: %.2f PLN\n", category, avgPrice));
        var bestDay = salesService.getDayWithHighestSales(products);
        System.out.println("Best day with highest sales: " + bestDay);


        SalesExporter exporter = new SalesExporter();
        Path jsonOutput = DIR.resolve("report.json");
        exporter.exportToJson(jsonOutput, products, salesService);
        System.out.println("Exported to file: " + jsonOutput.toAbsolutePath());

        Path csvOutput = DIR.resolve("summary.csv");
        var revenueCat = salesService.getRevenueByCategory(products);
        var avgPriceCat = salesService.getAverageUnitPriceByCategory(products);

        exporter.exportSummaryToCsv(csvOutput, revenueCat, avgPriceCat);
        System.out.println("Exported to:: " + csvOutput.toAbsolutePath());
    }
}