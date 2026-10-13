package zl4;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@SuperBuilder
public abstract class Product {
    String name;
    Double price;
    String category;

    protected String getDescription(Product product) {
        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\n", product.name, product.price, product.category);
    }
}
