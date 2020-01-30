package by.academy.it.travelcompany.travelitem.flight;

import by.academy.it.travelcompany.travelitem.currency.Currency;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Data
public class Flight {

    private Long id;
    private RouteMap routeMap;
    private LocalDateTime departureTime;
    private LocalDateTime arriveTime;
    private Currency currency;
    private Double ticketPrice;
    private String flightNumber;

    private Long searchId;
    private LocalDateTime checkedTime; // Last time Scanner update(create) this flight

    public Flight(Long id, RouteMap routeMap, LocalDateTime departureTime, LocalDateTime arriveTime, Currency currency, Double ticketPrice, String flightNumber) {
        this.id = id;
        this.routeMap = routeMap;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.currency = currency;
        this.ticketPrice = ticketPrice;
        this.flightNumber = flightNumber;
    }

    public Flight(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(routeMap, flight.routeMap) &&
                Objects.equals(departureTime.toLocalDate(), flight.departureTime.toLocalDate()) &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeMap, departureTime.toLocalDate(), flightNumber);
    }
}
