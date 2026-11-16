package pd11;

import pd11.model.Report;
import pd11.model.Sale;
import pd11.service.CsvSalesLoader;
import pd11.service.SalesExporter;
import pd11.service.SalesService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SalesApplication {

    private static final Path DIR = Path.of("src/main/resources");

    private final CsvSalesLoader loader = new CsvSalesLoader();
    private final SalesService salesService = new SalesService();
    private final SalesExporter exporter = new SalesExporter();

    public void run() throws IOException {
        List<Sale> products = loader.loadSales(DIR.resolve("sales.csv"));

        System.out.println("Initialized: " + products.size() + " records");

        Report report = exporter.createReport(salesService, products);

        displayReport(report);

        Path jsonOutput = DIR.resolve("report.json");
        exporter.exportToJson(jsonOutput, report);

        System.out.println("Exported JSON: " + jsonOutput.toAbsolutePath());

        Path csvOutput = DIR.resolve("summary.csv");
        exporter.exportSummaryToCsv(
            csvOutput,
            report.revenueByCategory(),
            report.averageUnitPriceByCategory()
        );

        System.out.println("Exported CSV: " + csvOutput.toAbsolutePath());
    }

    private void displayReport(Report report) {
        System.out.printf("Total Revenue: %.2f PLN%n", report.totalRevenue());

        System.out.println("Top 3 products by revenue:");
        report.top3Products().forEach(product ->
            System.out.printf(" - %s (ID: %s)%n",
                product.getProductName(),
                product.getProductId())
        );

        System.out.println("Revenue by Category:");
        report.revenueByCategory().forEach((category, revenue) ->
            System.out.printf(" - %s: %.2f PLN%n", category, revenue)
        );

        System.out.println("Average unit price by Category:");
        report.averageUnitPriceByCategory().forEach((category, avgPrice) ->
            System.out.printf(" - %s: %.2f PLN%n", category, avgPrice)
        );

        System.out.println("Best day with highest sales: "
            + report.dayWithHighestSales());
    }
}