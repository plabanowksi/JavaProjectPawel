package zl4;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Product p1 = FoodProduct.builder()
                .name("Vape")
                .price(99.99)
                .category("Electronics")
                .expiryDate(LocalDate.of(2026, 10, 20))
                .build();
        Product p2 = FoodProduct.builder()
                .name("Vodka")
                .price(77.99)
                .category("Alcohol")
                .expiryDate(LocalDate.of(2026, 10, 20))
                .build();
        Product p3 = Electronic.builder()
                .name("Wheel")
                .price(50.99)
                .category("Bikes")
                .warranty(3)
                .build();
        Product p4 = Electronic.builder()
                .name("Monitor")
                .price(12.99)
                .category("Electronics")
                .warranty(4)
                .build();
        Product p5 = FoodProduct.builder()
                .name("Karma dla kota")
                .price(24.99)
                .category("CatFood")
                .expiryDate(LocalDate.of(2027, 10, 20))
                .build();

        List<Product> productList = List.of(p1, p2, p3, p4, p5);

        for (Product product : productList) {
            System.out.println(product.getDescription(product));
        }
    }
}
