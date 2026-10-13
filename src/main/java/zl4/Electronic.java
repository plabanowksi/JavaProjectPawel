package zl4;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@SuperBuilder
public class Electronic extends Product {
    private final int warranty;

    @Override
    protected String getDescription(Product product) {
        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\nProduct warranty: %s\n",
                product.name, product.price, product.category, this.warranty);
    }
}
