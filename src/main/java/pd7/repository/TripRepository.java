
package pd7.repository;

import pd7.model.Trip;
import pd7.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TripRepository {

    private List<Trip> tripList = new ArrayList<>();

    public void addAllTrips(List<Trip> trips) {
        this.tripList = new ArrayList<>(trips);
    }

    public Optional<Trip> findByDestination(String destinationName) {
        if (destinationName == null || destinationName.isBlank()) {
            return Optional.empty();
        }

        return tripList.stream()
            .filter(Objects::nonNull)
            .filter(trip -> trip.getDestination() != null)
            .filter(trip -> destinationName.equalsIgnoreCase(
                trip.getDestination().getName()
            ))
            .findFirst();
    }

    public Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        if (user == null || trips == null) {
            return Optional.empty();
        }

        return trips.stream()
            .filter(Objects::nonNull)
            .filter(trip -> user.getPreferredTransport() == null
                || trip.getTransport() == user.getPreferredTransport())
            .filter(trip -> user.getBudget() == 0
                || trip.getPrice() <= user.getBudget())
            .findFirst();
    }
}
