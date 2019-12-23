package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightScanner {

    /**
     * This is scanner flights on ryanair.com and wizzair.com
     *
     * @param localDate                     starting date for parsing
     * @param dayQuantityForSearch Integer how deep to scan day
     * @param origin                        Airport origin
     * @param destination                   Airport destination
     *                                      new flight create by FlightServiceImpl
     */

    List<Flight> parseFlightsRY(LocalDate localDate, Integer dayQuantityForSearch, Airport origin, Airport destination, String direction);

    List <Flight> parseFlightsWIZZ(LocalDate localDate, Integer dayQuantityForSearch, Airport origin, Airport destination, String direction);



}
