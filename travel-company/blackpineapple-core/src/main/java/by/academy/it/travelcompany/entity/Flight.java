package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "price", "arriveDateTime", "updateDateTime","roundTripDirectList","roundTripReturnList"})
@ToString(exclude = {"roundTripDirectList","roundTripReturnList"})
@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private Long id;
    @Column(name = "FLIGHT_PRICE")
    private Double price;
    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;
    @Column(name = "FLIGHT_DEPARTURE_DATE_TIME")
    private LocalDateTime departureDateTime;
    @Column(name = "FLIGHT_ARRIVE_DATE_TIME")
    private LocalDateTime arriveDateTime;
    @Column(name = "FLIGHT_UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTEMAP_ID")
    private RouteMap routeMap;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @OneToMany(mappedBy = "directFlight", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RoundTrip> roundTripDirectList;

    @OneToMany(mappedBy = "returnFlight", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RoundTrip> roundTripReturnList;

}
