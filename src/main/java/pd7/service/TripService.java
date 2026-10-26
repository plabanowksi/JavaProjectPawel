package pd7.service;

import pd7.model.Trip;
import pd7.model.User;
import java.util.List;
import java.util.Optional;

public class TripService {
    public Optional<Trip> findByDestination(List<Trip> trips, String destinationName) {
        return trips.stream()
            .filter(trip -> trip.getDestination().getName().equals(destinationName))
            .findFirst();
    }

    public Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        if (user.getPreferredTransport() == null && user.getBudget() != 0) {
            return Optional.empty();
        }
        return trips.stream()
            .filter(trip -> user.getPreferredTransport() == null
                || trip.getTransport() == user.getPreferredTransport())
            .filter(trip -> user.getBudget() == 0
                || trip.getPrice() < user.getBudget())
            .findFirst();
    }

    public String buildTripDescription(User user, List<Trip> trips) {
        return findBestTrip(user, trips)
            .map(trip -> trip.getDestination() + " + "
                + trip.getTransport() + " + "
                + trip.getPrice())
            .orElse("No trip available");
    }
}
