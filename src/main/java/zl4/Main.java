package zl4;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Electronic("Vape", 99.99, "Electronics", 4);
        Product p2 = new FoodProduct("Vodka", 77.99, "Alcohol", LocalDate.parse("2127-02-20"));
        Product p3 = new Electronic("Wheel", 50.99, "Bikes", 3);
        Product p4 = new Electronic("Monitor", 12.99, "Electronics", 4);
        Product p5 = new FoodProduct("Karma dla kota", 24.99, "CatFood", LocalDate.parse("2027-02-20"));
        List<Product> productList = List.of(p1, p2, p3, p4, p5);

        for (Product product : productList) {
            System.out.println(product.getDescription(product));
        }
    }
}
