package pd11.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Sale {
    private final LocalDate date;
    private final String productId;
    private final String productName;
    private final String category;
    private final int quantity;
    private final BigDecimal unitPrice;

    public Sale(LocalDate date, String productId, String productName,
                String category, int quantity, BigDecimal unitPrice) {

        this.date = Objects.requireNonNull(date, "Date cannot be null");

        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }

        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative or null");
        }

        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
