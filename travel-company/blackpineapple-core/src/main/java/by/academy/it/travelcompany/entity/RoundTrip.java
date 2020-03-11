package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "ROUND_TRIP")
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
}
