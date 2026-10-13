package pd4;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Movie extends Resource {
    private final int timeInMinutes;

    protected double calculateRentalCost(int days, double price) {
        return days * (0.05 * price);
    }

    @Override
    public int compareTo(Resource o) {
        return Double.compare(this.price, o.price);
    }
}
