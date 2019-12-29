package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.flight.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightScanner {


    /**
     * This is scanner flights on ryanair.com and wizzair.com
     *
     * @param localDate                     Starting date for parsing
     * @param dayQuantityForSearch          Integer how deep to scan day
     * @param origin                        Airport origin
     * @param destination                   Airport destination
     * @param direction                     Additional information for other classes
     *
     *                                      new flight create by FlightServiceImpl with generated searchId
     */

    List<Flight> parseFlightsRY(LocalDate localDate, Integer dayQuantityForSearch, Airport origin, Airport destination, String direction);

    List <Flight> parseFlightsWIZZ(LocalDate localDate, Integer dayQuantityForSearch, Airport origin, Airport destination, String direction);

    /**
     * Long searchId helps to sort and chose needed Flight Flight Service or Trip Service
     * @return Long searchId
     */

    public Long getSearchId();

    /**
     * Long searchId helps to sort and chose needed Flight Flight Service or Trip Service
     * @param searchId to set
     */

    void setSearchId(Long searchId);

}
