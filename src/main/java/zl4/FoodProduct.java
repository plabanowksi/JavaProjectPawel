package zl4;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@RequiredArgsConstructor
@SuperBuilder
public class FoodProduct extends Product {
    LocalDate expiryDate;

    @Override
    protected String getDescription(Product product) {
        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\nProduct expiryDate: %s\n",
                product.name, product.price, product.category, this.expiryDate);
    }
}
