package pd4;

public abstract class Resource implements Comparable<Resource> {
    private final int id;
    private final String name;
    protected final double price;
    protected final ResourceType type;


    protected Resource(int id, String name, double price, ResourceType type) {
        if (price <=0){
            throw new IllegalArgumentException();
        }
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

    public ResourceType getType() {
        return type;
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
