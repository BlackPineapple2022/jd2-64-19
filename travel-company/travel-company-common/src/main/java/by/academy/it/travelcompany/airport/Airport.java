package by.academy.it.travelcompany.airport;

/**
 * This class describes airport and has field:
 * code - this defining information, IATA code,
 * equals and hashcode overriding by this field,
 * country and city fields only for additional information
 */

import java.util.Objects;
import java.util.Set;

public class Airport implements Comparable<Airport> {
    private String code;
    private String country;
    private String city;

    public Airport() {
    }

    public Airport(String code, String country, String city) {
        this.code = code;
        this.country = country;
        this.city = city;
    }

    public Airport(String code) {
        this.code = code;
        Set<Airport> airports = AirportInfoCentre.getAllAirports();
        for (Airport a : airports) {
            if (code.equals(a.getCode())) {
                this.city = a.getCity();
                this.country = a.getCountry();
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return country + " | " + city + " | " + code;
    }

    @Override
    public int compareTo(Airport a) {
        //int result = 0;
        int result = country.compareTo(a.getCountry());
        if (result == 0) {
            result = city.compareTo(a.getCity());
        }
        if (result == 0){
            result = code.compareTo(a.getCode());
        }
        return result;
    }
}
