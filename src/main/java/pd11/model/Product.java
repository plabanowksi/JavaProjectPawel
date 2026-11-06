package pd11.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class Product {
    private final LocalDate date;
    private final String productId;
    private final String productName;
    private final String category;
    private final int quantity;
    private final double unitPrice;
}
