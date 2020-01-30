package by.academy.it.travelcompany.travelitem.airport;

/**
 * This class describes airport and has field:
 * code - this defining information, IATA code,
 * equals and hashcode overriding by this field,
 * country and city fields only for additional information
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport implements Comparable<Airport> {
    private Long id;
    private String code;
    private String country;
    private String city;

    @Deprecated
    public Airport(String code, String country, String city) {
        this.code = code;
        this.country = country;
        this.city = city;
    }

    @Deprecated
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
        return country + " | " + city + " | " + code + " | "+id;
    }

    @Override
    public int compareTo(Airport a) {
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
