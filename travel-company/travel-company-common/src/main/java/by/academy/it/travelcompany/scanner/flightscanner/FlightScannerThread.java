package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.time.LocalDate;

public class FlightScannerThread extends Thread {

    private String direction;
    private Airline airline;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalDate startingDate;
    private Integer dayQuantityForSearch;

    private Long searchId;

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public FlightScannerThread(Airline airline, Airport originAirport, Airport destinationAirport, LocalDate startingDate, Integer dayQuantityForSearch, String direction) {
        this.airline = airline;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.startingDate = startingDate;
        this.dayQuantityForSearch = dayQuantityForSearch;
        this.direction = direction;
    }


    @Override
    public void run() {
        FlightScanner flightScanner = new FlightScannerImpl();
        flightScanner.setSearchId(searchId);

        if (airline.equals(Airline.RY)) {
            flightScanner.parseFlightsRY(startingDate, dayQuantityForSearch, originAirport, destinationAirport, direction);
        }
        if (airline.equals(Airline.WIZZ)) {
            flightScanner.parseFlightsWIZZ(startingDate, dayQuantityForSearch, originAirport, destinationAirport, direction);
        }
    }


}
