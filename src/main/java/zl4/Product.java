package zl4;

public abstract class Product {
    String name;
    Double price;
    String category;

    protected Product(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    protected String getDescription(Product product) {

        return String.format("Product name: %s \nProduct price: %s\nProduct category: %s\n", product.name, product.price, product.category);
    }
}
