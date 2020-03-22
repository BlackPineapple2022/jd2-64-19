package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"favouriteList"})
@Entity
@Table(name = "ROUND_TRIP")
@EqualsAndHashCode(exclude = {"id","favouriteList","price"})
public class RoundTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUND_TRIP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIRECT_FLIGHT_ID")
    private Flight directFlight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RETURN_FLIGHT_ID")
    private Flight returnFlight;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roundTripList")
    private List<Favourite> favouriteList;

    @Transient
    private Double price;

    public RoundTrip(Flight directFlight, Flight returnFlight, Double price) {
        this.directFlight = directFlight;
        this.returnFlight = returnFlight;
        this.price = price;
    }


}
