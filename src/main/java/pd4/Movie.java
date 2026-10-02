package pd4;

public class Movie extends Resource {
    final int timeInMinutes;

    protected Movie(int id, String name, double price, ResourceType type, int timeInMinutes) {
        super(id, name, price, type);
        this.timeInMinutes = timeInMinutes;
    }

    protected double calculateRentalCost(int days, double price) {
        return days * (0.05 * price);
    }

    @Override
    public int compareTo(Resource o) {
        return Double.compare(this.price, o.price);
    }
}
