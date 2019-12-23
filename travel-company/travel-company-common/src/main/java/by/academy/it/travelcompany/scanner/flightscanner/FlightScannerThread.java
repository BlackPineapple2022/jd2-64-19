package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import java.time.LocalDate;

public class FlightScannerThread extends Thread {
    private String direction;
    private Airline airline;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalDate startingDate;
    private Integer dayQuantityForSearch;

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
        if (airline.equals(Airline.RY)) {
            flightScanner.parseFlightsRY(startingDate,dayQuantityForSearch,originAirport,destinationAirport,direction);

            //FlightService flightService = FlightServiceImpl.getInstance();
            //System.out.println(flightService.getAllFlights());
        }
        if (airline.equals(Airline.WIZZ)){
            flightScanner.parseFlightsWIZZ(startingDate,dayQuantityForSearch,originAirport,destinationAirport,direction);
           //FlightService flightService = FlightServiceImpl.getInstance();
           //System.out.println(flightService.getAllFlights());
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public Integer getDayQuantityForSearch() {
        return dayQuantityForSearch;
    }

    public void setDayQuantityForSearch(Integer dayQuantityForSearch) {
        this.dayQuantityForSearch = dayQuantityForSearch;
    }
}
