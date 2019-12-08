package by.academy.it.travelcompany.accommodation;

import java.time.LocalDate;

public class Accommodation {
    private String country;
    private String city;
    private String address;
    private String nameOfAccommodation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Accomodations accomodations;
    private long id;

    private static long idCount = 1L;

    public static long getIdCount() {
        return idCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Accommodation() {
    }

    public Accommodation(String country, String city, String address, String nameOfAccommodation, LocalDate checkInDate, LocalDate checkOutDate, Accomodations accomodations) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.nameOfAccommodation = nameOfAccommodation;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.accomodations = accomodations;
        this.id = idCount;
        idCount++;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfAccommodation() {
        return nameOfAccommodation;
    }

    public void setNameOfAccommodation(String nameOfAccommodation) {
        this.nameOfAccommodation = nameOfAccommodation;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Accomodations getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(Accomodations accomodations) {
        this.accomodations = accomodations;
    }
}
