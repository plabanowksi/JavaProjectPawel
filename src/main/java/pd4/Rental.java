package pd4;

public class Rental {
    Resource resource;
    int days;
    RentalStatus status;

    public Rental(Resource resource, int days, RentalStatus status) {
        this.resource = resource;
        this.days = days;
        this.status = status;
    }

    public Resource getResource() {
        return resource;
    }

    public RentalStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "resource=" + resource +
                ", days=" + days +
                ", status=" + status +
                '}';
    }
}
