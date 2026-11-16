package pd11.service;

import pd11.model.Sale;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CsvSalesLoader {
    public List<Sale> loadSales(Path csvPath) throws IOException {
        try (var lines = Files.lines(csvPath)) {
            return lines
                .skip(1)
                .filter(line -> !line.isBlank())
                .map(this::parseProduct)
                .filter(Objects::nonNull)
                .toList();
        }
    }

    private Sale parseProduct(String line) {
        try {
            String[] parts = line.split(",", -1);

            if (parts.length != 6) {
                throw new IllegalArgumentException("Incorrect columns:");
            }

            LocalDate date = LocalDate.parse(parts[0].trim());
            String productId = parts[1];
            String productName = parts[2];
            String category = parts[3];
            int quantity = Integer.parseInt(parts[4]);
            BigDecimal unitPrice = new BigDecimal(parts[5]);
            return new Sale(date, productId, productName,
                category, quantity, unitPrice);

        } catch (IllegalArgumentException e) {
            System.err.println("Incorrect row: " + line + " | " + e.getMessage());
            return null;
        }
    }
}