package pd7.service;

import pd7.model.Trip;
import pd7.model.User;
import pd7.repository.TripRepository;
import java.util.List;
import java.util.Optional;

public class TripService {

    private final TripRepository tripRepository;
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
    public Optional<Trip> findByDestination(String destinationName) {
        return tripRepository.findByDestination(destinationName);
    }

    public Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        return tripRepository.findBestTrip(user, trips);
    }

    public String buildTripDescription(User user, List<Trip> trips) {
        return findBestTrip(user, trips)
            .map(trip -> trip.getDestination().getName() + " - "
                + trip.getTransport() + " - "
                + trip.getPrice())
            .orElse("No trip available");
    }
}