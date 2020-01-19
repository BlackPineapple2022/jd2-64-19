package by.academy.it.travelcompany.travelitem.trip;

import by.academy.it.travelcompany.travelitem.features.accommodation.Accommodation;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.features.transfer.Transfer;

import java.util.List;
import java.util.Objects;

public class Trip {

    private Long id;
    private List<Flight> flights;
    private List<Accommodation> accommodations;
    private List<Transfer> transfers;
    private Double price;
    private Long searchId;

    public Trip() {
    }

    public Trip(List<Flight> flights, Double price, Long searchId) {
        this.flights = flights;
        this.price = price;
        this.searchId = searchId;
    }

    public Trip(Long id, List<Flight> flights, List<Accommodation> accommodations, List<Transfer> transfers) {
        this.id = id;
        this.flights = flights;
        this.accommodations = accommodations;
        this.transfers = transfers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
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
                ", price=" + price +

                //", accommodations=" + accommodations +
                //", transfers=" + transfers +
                '}';
    }
}
