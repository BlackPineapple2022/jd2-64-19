package by.academy.it.travelcompany.trip;

import by.academy.it.travelcompany.accommodation.Accommodation;
import by.academy.it.travelcompany.flight.Flight;

import java.util.List;

public class Trip {

    private Flight flightFrom;
    private Flight flightTo;
    private List <Accommodation> accommodations;
    private long id;

    private static long idCount = 1L;

    public Trip() {
    }

    public Trip(Flight flightFrom, Flight flightTo, List<Accommodation> accommodations) {
        this.id = idCount;
        idCount++;

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

    public static long getIdCount() {
        return idCount;
    }

}
