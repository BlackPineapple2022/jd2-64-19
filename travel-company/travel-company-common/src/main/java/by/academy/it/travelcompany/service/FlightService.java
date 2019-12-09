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
     */

    void addFlight(Flight flight);


    void deleteFlight(long id);

    void updateFlight(Flight flight);

}
