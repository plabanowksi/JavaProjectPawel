package pd11.service;

import pd11.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CsvSalesLoader {
    public List<Product> loadSales(Path csvPath) throws IOException {
        try (var lines = Files.lines(csvPath)) {
            return lines
                .skip(1)
                .filter(line -> !line.isBlank())
                .map(this::parseProduct)
                .filter(Objects::nonNull)
                .toList();
        }
    }

    private Product parseProduct(String line) {
        try {
            String[] parts = line.split(",");

            if (parts.length != 6) {
                throw new IllegalArgumentException("Incorrect columns:");
            }

            LocalDate date = LocalDate.parse(parts[0]);
            String productId = parts[1];
            String productName = parts[2];
            String category = parts[3];
            int quantity = Integer.parseInt(parts[4]);
            double unitPrice = Double.parseDouble(parts[5]);

            return new Product(date, productId, productName,
                category, quantity, unitPrice);

        } catch (Exception e) {
            System.err.println("Incorrect row: " + line + " | " + e.getMessage());
            return null;
        }
    }
}