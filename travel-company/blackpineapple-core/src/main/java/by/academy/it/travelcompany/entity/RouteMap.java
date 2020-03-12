package by.academy.it.travelcompany.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id","flightList","flightJournal"})
@Entity
@Table(name = "ROUTEMAP")
public class RouteMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTEMAP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AIRLINE_ID")
    private Airline airline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AIRPORT_ORIGIN_ID")
    private Airport airportOrigin;

    @JoinColumn(name = "AIRPORT_DESTINATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Airport airportDestination;

    @JoinColumn(name = "DIRECTION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Direction direction;

    @OneToMany(mappedBy = "routeMap", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Flight> flightList;

    @OneToOne (mappedBy = "routeMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FlightJournal flightJournal;

    public RouteMap(Airline airline, Airport airportOrigin, Airport airportDestination, Direction direction) {
        this.airline = airline;
        this.direction = direction;
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
    }

    @Override
    public String toString() {
        return airline.getName()+"--"+airportOrigin.getCode()+"--"+airportDestination.getCode()+"--"+direction.getName();
    }
}
