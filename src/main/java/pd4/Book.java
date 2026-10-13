package pd4;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Book extends Resource {
    private final String author;

    protected double calculateRentalCost(int days, double price) {
        return days * (0.01 * price);
    }

    @Override
    public int compareTo(Resource o) {
        return Double.compare(this.price, o.price);
    }
}
