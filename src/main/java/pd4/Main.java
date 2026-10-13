package pd4;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();
        Movie movie1 = Movie.builder()
                .id(44).name("Scream").price(99.99).type(ResourceType.MOVIE).timeInMinutes(150)
                .build();
        Rental rental1 = Rental.builder()
                .resource(movie1).days(20).status(RentalStatus.ACTIVE)
                .build();
        rentalSystem.addRental(rental1);
        Book book1 = Book.builder()
                .id(1).name("Gandalf the White").price(99.99).type(ResourceType.BOOK).author("J. R. R. Tolkien")
                .build();
        Rental rental2 = Rental.builder()
                .resource(book1).days(20).status(RentalStatus.ACTIVE)
                .build();
        rentalSystem.addRental(rental2);
        Book book2 = Book.builder()
                .id(1).name("Hobbit").price(79.99).type(ResourceType.BOOK).author("J. R. R. Tolkien")
                .build();
        Rental rental3 = Rental.builder()
                .resource(book2).days(20).status(RentalStatus.RETURNED)
                .build();
        rentalSystem.addRental(rental3);

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
}
