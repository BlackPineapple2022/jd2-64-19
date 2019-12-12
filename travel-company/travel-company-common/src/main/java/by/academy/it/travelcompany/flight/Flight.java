package by.academy.it.travelcompany.flight;

import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.AirportInfoCentre;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {

    private Airport originAirport;
    private Airport destinationAirport;

    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;

    private Airline airline;
    private double ticketPrice;
    private String flightNumber;

    private long id;

    public Flight() {
    }

    public Flight(long id,Airport originAirport, Airport destinationAirport, LocalDateTime arriveTime, LocalDateTime departureTime, Airline airline, double ticketPrice, String flightNumber) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Double.compare(flight.ticketPrice, ticketPrice) == 0 &&
                id == flight.id &&
                Objects.equals(originAirport, flight.originAirport) &&
                Objects.equals(destinationAirport, flight.destinationAirport) &&
                Objects.equals(arriveTime, flight.arriveTime) &&
                Objects.equals(departureTime, flight.departureTime) &&
                airline == flight.airline &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originAirport, destinationAirport, arriveTime, departureTime, airline, ticketPrice, flightNumber);
    }

    @Override
    public String toString() {
        return "Flight{" +
                ", id=" + id +
                "originAirport=" + originAirport +
                ", destinationAirport=" + destinationAirport +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", airline=" + airline +
                ", ticketPrice=" + ticketPrice +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }
}
