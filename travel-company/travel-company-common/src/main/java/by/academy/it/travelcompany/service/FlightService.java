package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.flight.Flight;

import java.util.List;

/**
 * Flight service
 */

public interface FlightService {

    /**
     * Get all flights
     * @return list of found flights
     */

    List <Flight> getAllFlights();

    /**
     * Add new flight
     * @param flight flight to save
     * @return new flight with generated Id
     */

    Flight addFlight(Flight flight);

    /**
     * Delete flight by id
     * flight with @param id delete from list
     */

    void deleteFlightById(Long id);

    /**
     * update object in list with same id of
     * @param flight flight to update (by id)
     * @return flight updated flight
     */

    Flight updateFlightById(Flight flight);

    /**
     * update object in list with same LocalDateTime arriveTime
     * @param flight flight to update (by LocalTime arriveTime )
     * @return flight updated flight
     * If flight with LocalDateTime arriveTime doesn't exist on list
     * It will be created
     */

    Flight updateOrCreateByLocalDateTime(Flight flight);

}
