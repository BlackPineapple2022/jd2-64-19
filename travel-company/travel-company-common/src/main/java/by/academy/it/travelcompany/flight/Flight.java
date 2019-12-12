package by.academy.it.travelcompany.flight;

import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.Airline;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {

    private Long id;

    private Airport originAirport;
    private Airport destinationAirport;

    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;

    private Airline airline;
    private double ticketPrice;
    private String flightNumber;

    public Flight() {
    }

    public Flight(Long id, Airport originAirport, Airport destinationAirport, LocalDateTime arriveTime, LocalDateTime departureTime, Airline airline, double ticketPrice, String flightNumber) {
        this.id = id;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.airline = airline;
        this.ticketPrice = ticketPrice;
        this.flightNumber = flightNumber;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Double.compare(flight.ticketPrice, ticketPrice) == 0 &&
                Objects.equals(id, flight.id) &&
                Objects.equals(originAirport, flight.originAirport) &&
                Objects.equals(destinationAirport, flight.destinationAirport) &&
                Objects.equals(arriveTime, flight.arriveTime) &&
                Objects.equals(departureTime, flight.departureTime) &&
                airline == flight.airline &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", originAirport=" + originAirport +
                ", destinationAirport=" + destinationAirport +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", airline=" + airline +
                ", ticketPrice=" + ticketPrice +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originAirport, destinationAirport, arriveTime, departureTime, airline, ticketPrice, flightNumber);
    }

}