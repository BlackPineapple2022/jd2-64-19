package by.academy.it.travelcompany.travelitem.flight;

import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {

    private Long id;

    private Airport originAirport;
    private Airport destinationAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arriveTime;

    private AirlineEnum airlineEnum;

    private String currency;
    private Double ticketPrice;
    private String flightNumber;

    private String direction;

    private Long searchId;

    private LocalDateTime checkedTime; // Last time Scanner update(create) this flight

    public Flight() {
    }

    public Flight(Long id, Airport originAirport, Airport destinationAirport, LocalDateTime departureTime, LocalDateTime arriveTime, AirlineEnum airlineEnum, String currency, Double ticketPrice, String flightNumber) {
        this.id = id;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.airlineEnum = airlineEnum;
        this.currency = currency;
        this.ticketPrice = ticketPrice;
        this.flightNumber = flightNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public AirlineEnum getAirlineEnum() {
        return airlineEnum;
    }

    public void setAirlineEnum(AirlineEnum airlineEnum) {
        this.airlineEnum = airlineEnum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalDateTime getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(LocalDateTime checkedTime) {
        this.checkedTime = checkedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return
                Objects.equals(originAirport, flight.originAirport) &&
                Objects.equals(destinationAirport, flight.destinationAirport) &&
                Objects.equals(departureTime.getDayOfYear(), flight.departureTime.getDayOfYear()) &&
                airlineEnum == flight.airlineEnum &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", originAirport=" + originAirport +
                ", destinationAirport=" + destinationAirport +
                ", departureTime=" + departureTime +
                ", arriveTime=" + arriveTime +
                ", airlineEnum=" + airlineEnum +
                ", currency =" + currency +
                ", ticketPrice=" + ticketPrice +
                ", flightNumber='" + flightNumber + '\'' +
                ", direction='" + direction + '\'' +
                ", searchId='" + searchId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originAirport, destinationAirport,departureTime, arriveTime, airlineEnum, ticketPrice, flightNumber);
    }

}
