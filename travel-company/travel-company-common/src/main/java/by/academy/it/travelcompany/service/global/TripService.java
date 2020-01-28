package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.trip.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Trip service
 */

public interface TripService {

    /**
     * Get all trips
     *
     * @return list of found trips
     */

    List<Trip> getAllTrips();

    /**
     * Add new trip
     *
     * @param trip trip to save
     * @return new trip with generated id
     */

    Trip addTrip(Trip trip);

    /**
     * Delete trip by id
     * trip with @param id delete from list
     */

    void deleteTrip(Long id);

    /**
     * update object in list with same id of
     *
     * @param trip trip to update (by id)
     * @return trip updated transfer
     */

    Trip updateTrip(Trip trip);

    List<Trip> getAllTripBySearchId(Long searchId);

    void deleteAllBySearchIdButFavourite(List<Long> searchIdList);

    Map<Long,List<Trip>> getFavouriteTripsByUserId(Long userId);

    Trip getTripById(Long id);

    void updatePrice(Long tripId, Double price);
}
