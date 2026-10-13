package pd4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@RequiredArgsConstructor
@SuperBuilder
public abstract class Resource implements Comparable<Resource> {
    private final int id;
    private final String name;
    protected final double price;
    protected final ResourceType type;

    protected abstract double calculateRentalCost(int days, double price);

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
