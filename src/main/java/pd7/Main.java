
package pd7;

import pd7.model.Destination;
import pd7.model.TransportType;
import pd7.model.Trip;
import pd7.model.User;
import pd7.repository.TripRepository;
import pd7.service.TripService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Trip> listOfTrips = List.of(
            new Trip(new Destination("Poland", "Bialystok"), TransportType.CAR, 99.99, 9),
            new Trip(new Destination("Ukraine", "Kiev"), TransportType.PLANE, 99.99, 3),
            new Trip(new Destination("Russia", "Moscov"), TransportType.TRAIN, 99.99, 5),
            new Trip(new Destination("Czech", "Prague"), TransportType.TRAIN, 99.99, 12)
        );

        TripRepository tripRepository = new TripRepository();
        tripRepository.addAllTrips(listOfTrips);
        TripService tripService = new TripService(tripRepository);

        User user = new User("pawel", TransportType.PLANE, 200.0);

        System.out.println(tripService.findByDestination("Russia"));
        System.out.println(tripService.findBestTrip(user, listOfTrips));
        System.out.println(tripService.buildTripDescription(user, listOfTrips));
    }
}
