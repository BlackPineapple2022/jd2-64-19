package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.trip.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TripServiceImpl implements TripService {

    private static final TripService INSTANCE = new TripServiceImpl();
    private final List<Trip> trips = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);

    private TripServiceImpl(){
    }

    public static TripService getService(){
        return INSTANCE;
    }

    @Override
    public List<Trip> getAllTrips() {
        return trips;
    }

    @Override
    public Trip addTrip(Trip trip) {
        trip.setId(sequence.incrementAndGet());
        trips.add(trip);
        return trip;
    }

    @Override
    public void deleteTrip(Long id) {
        trips.removeIf(t->t.getId().equals(id));
    }

    @Override
    public Trip updateTrip(Trip trip) {
        deleteTrip(trip.getId());
        trips.add(trip);
        return trip;
    }

}
