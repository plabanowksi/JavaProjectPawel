package pd4;

public class Book extends Resource {
    private final String author;

    protected Book(int id, String name, double price, ResourceType type, String author) {
        super(id, name, price, type);
        this.author = author;
    }

    protected double calculateRentalCost(int days, double price) {
        return days * (0.01 * price);
    }

    @Override
    public int compareTo(Resource o) {
        return Double.compare(this.price, o.price);
    }
}
