package by.academy.it.travelcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRAVEL_POINT")
public class TravelPoint {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TRAVEL_POINT_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long travelPointId;
    @Column(name = "TRAVEL_POINT_NAME")
    private String travelPointName;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "travelPoint",cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    private TravelPointDetail travelPointDetail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TRAVEL_POINT_EXCURSION", joinColumns = {@JoinColumn(name ="TRAVEL_POINT_ID")},
    inverseJoinColumns = {@JoinColumn(name = "EXCURSION_ID")})
    private List<Excursion> excursions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SIGHTSEEING_ID")
    private Sightseeing sightseeing;

}
