package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"routeMapOriginList", "routeMapDestinationList"})
@EqualsAndHashCode(exclude = {"id", "routeMapOriginList", "routeMapDestinationList"})
@Entity
@Table(name = "AIRPORT")

public class Airport implements Comparable<Airport>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRPORT_ID")
    private Long id;
    @Column(name = "AIRPORT_CODE")
    private String code;
    @Column(name = "AIRPORT_CITY")
    private String city;
    @Column(name = "AIRPORT_COUNTRY")
    private String country;

    @OneToMany(mappedBy = "airportOrigin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteMap> routeMapOriginList;

    @OneToMany(mappedBy = "airportDestination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteMap> routeMapDestinationList;

    public Airport(String code, String country, String city) {
        this.city = city;
        this.code = code;
        this.country = country;
    }

    public Airport(String code) {
        this.code = code;
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
