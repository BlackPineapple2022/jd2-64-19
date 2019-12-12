package by.academy.it.travelcompany.trip;

import by.academy.it.travelcompany.accommodation.Accommodation;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.transfer.Transfer;

import java.util.List;
import java.util.Objects;

public class Trip {

    private Long id;
    private List<Flight> flights;
    private List<Accommodation> accommodations;
    private List<Transfer> transfers;

    public Trip() {
    }

    public Trip(Long id, List<Flight> flights, List<Accommodation> accommodations, List<Transfer> transfers) {
        this.id = id;
        this.flights = flights;
        this.accommodations = accommodations;
        this.transfers = transfers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) &&
                Objects.equals(flights, trip.flights) &&
                Objects.equals(accommodations, trip.accommodations) &&
                Objects.equals(transfers, trip.transfers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flights, accommodations, transfers);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", flights=" + flights +
                ", accommodations=" + accommodations +
                ", transfers=" + transfers +
                '}';
    }
}
