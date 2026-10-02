package pd4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RentalSystem {

    private final List<Rental> rentals;

    public RentalSystem() {
        this.rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    double calculateTotalCost() {
        double totalCost = 0;
        for (Rental rental : rentals) {

            if (rental.resource.type == ResourceType.BOOK) {
                totalCost += rental.resource.calculateRentalCost(rental.days, rental.resource.getPrice());
            }
            if (rental.resource.type == ResourceType.MOVIE) {
                totalCost = rental.resource.calculateRentalCost(rental.days, rental.resource.getPrice());

            }
        }

        return Math.round(totalCost * 100.0) / 100.0;
    }

    int countByStatus(RentalStatus rentalStatus) {
        int count = 0;
        for (Rental rental : rentals) {
            if (rental.getStatus() == rentalStatus) {
                count++;
            }
        }
        return count;
    }

    void sortByBasePrice() {
        rentals.sort(Comparator.comparing(rental -> rental.getResource().getPrice()));
    }

    void sortByName() {
        rentals.sort(Comparator.comparing(rental -> rental.getResource().getName()));
    }

    void printRentals() {
        for (Rental resource : rentals) {
            System.out.println(resource);
        }
    }

    @Override
    public String toString() {
        return "RentalSystem{" +
                "rentals=" + rentals +
                '}';
    }
}
