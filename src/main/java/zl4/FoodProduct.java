package zl4;

import java.time.LocalDate;

public class FoodProduct extends Product {
    LocalDate expiryDate;

    FoodProduct(String name, Double price, String category, LocalDate expiryDate) {
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    @Override
    protected String getDescription(Product product) {
        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\nProduct expiryDate: %s\n",
                product.name, product.price, product.category, this.expiryDate);
    }
}
