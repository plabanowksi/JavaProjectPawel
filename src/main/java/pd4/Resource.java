package pd4;

public abstract class Resource implements Comparable<Resource> {
    final int id;
    final String name;
    final double price;
    final ResourceType type;

    protected Resource(int id, String name, double price, ResourceType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    protected abstract double calculateRentalCost(int days, double price);

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
