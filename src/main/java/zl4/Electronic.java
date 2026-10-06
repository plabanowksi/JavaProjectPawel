package zl4;

public class Electronic extends Product {
    private final int warranty;

    Electronic(String name, Double price, String category, int warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    @Override
    protected String getDescription(Product product) {
        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\nProduct warranty: %s\n",
                product.name, product.price, product.category, this.warranty);
    }
}
