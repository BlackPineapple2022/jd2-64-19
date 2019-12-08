package by.academy.it.travelcompany.flight;

import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.AirportInfoCentre;

import java.time.LocalDateTime;

public class Flight {

    private Airport originAirport;
    private Airport destinationAirport;

    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;

    private Airline airline;
    private double ticketPrice;
    private String flightNumber;
    private long id;

    private static long idCount = 1L;
    public Flight() {
    }

    public Flight(Airport originAirport, Airport destinationAirport, LocalDateTime arriveTime, LocalDateTime departureTime, Airline airline, double ticketPrice, String flightNumber) {
        id = idCount;
        idCount++;

        if (AirportInfoCentre.getAllDestinations(originAirport).contains(destinationAirport)) {
            this.originAirport = originAirport;
            this.destinationAirport = destinationAirport;
            this.arriveTime = arriveTime;
            this.departureTime = departureTime;
            this.airline = airline;
            this.ticketPrice = ticketPrice;
            this.flightNumber = flightNumber;
        } else {
            this.originAirport = null;
            this.destinationAirport = null;
            this.arriveTime = null;
            this.departureTime = null;
            this.airline = null;
            this.ticketPrice = 0.0;
            this.flightNumber = null;
        }
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public Airline getAirline() {
        return airline;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public long getId() {
        return id;
    }

    public static long getIdCount() {
        return idCount;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setId(long id) {
        this.id = id;
    }
}
