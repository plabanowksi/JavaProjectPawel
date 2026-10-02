package pd4;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = getRentalSystem();
        printSummaryForRentals(rentalSystem);
    }

    private static void printSummaryForRentals(RentalSystem rentalSystem) {
        System.out.println("Calculate total cost for all rentals");
        System.out.println(rentalSystem.calculateTotalCost() + " PLN");
        System.out.println("Filter order by RentalStatus = Active");
        System.out.println("There are " + rentalSystem.countByStatus(RentalStatus.ACTIVE) + " rentals with this status ");
        System.out.println("Sort by base price");
        rentalSystem.sortByBasePrice();
        rentalSystem.printRentals();
        System.out.println("Sort by name");
        rentalSystem.sortByName();
        rentalSystem.printRentals();
    }

    private static RentalSystem getRentalSystem() {
        RentalSystem rentalSystem = new RentalSystem();
        Movie movie1 = new Movie(44, "Scream", 29.99, ResourceType.MOVIE, 150);
        Rental rental1 = new Rental(movie1, 20, RentalStatus.ACTIVE);
        rentalSystem.addRental(rental1);

        Book book1 = new Book(1, "Gandalf the White", 159.99, ResourceType.BOOK, "J. R. R. Tolkien");
        Rental rental2 = new Rental(book1, 20, RentalStatus.ACTIVE);
        rentalSystem.addRental(rental2);

        Book book2 = new Book(2, "Hobbit", 79.99, ResourceType.BOOK, "J. R. R. Tolkien");
        Rental rental3 = new Rental(book2, 20, RentalStatus.RETURNED);
        rentalSystem.addRental(rental3);
        return rentalSystem;
    }
}
