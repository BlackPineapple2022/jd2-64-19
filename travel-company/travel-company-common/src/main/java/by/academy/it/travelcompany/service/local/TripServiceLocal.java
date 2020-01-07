package by.academy.it.travelcompany.service.local;


import by.academy.it.travelcompany.travelitem.trip.Trip;

import java.util.List;

/**
 * Trip service
 */

public interface TripServiceLocal {

    /**
     * Get all trips
     * @return list of found trips
     */

    List<Trip> getAllTrips();

    /**
     * Add new trip
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
     * @param trip trip to update (by id)
     * @return trip updated transfer
     */

    Trip updateTrip(Trip trip);

    List <Trip> getAllTripBySearchId(Long searchId);


}
