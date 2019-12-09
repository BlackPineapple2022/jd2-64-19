package by.academy.it.travelcompany.trip;

import by.academy.it.travelcompany.accommodation.Accommodation;
import by.academy.it.travelcompany.flight.Flight;

import java.util.List;
import java.util.Objects;

public class Trip {

    private Flight flightFrom;
    private Flight flightTo;
    private List<Accommodation> accommodations;
    private long id;

    public Trip() {
    }

    public Trip(long id, Flight flightFrom, Flight flightTo, List<Accommodation> accommodations) {

        this.id = id;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.accommodations = accommodations;

    }

    public Flight getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(Flight flightFrom) {
        this.flightFrom = flightFrom;
    }

    public Flight getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(Flight flightTo) {
        this.flightTo = flightTo;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                Objects.equals(flightFrom, trip.flightFrom) &&
                Objects.equals(flightTo, trip.flightTo) &&
                Objects.equals(accommodations, trip.accommodations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightFrom, flightTo, accommodations, id);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "flightFrom=" + flightFrom +
                ", flightTo=" + flightTo +
                ", accommodations=" + accommodations +
                ", id=" + id +
                '}';
    }
}
