package by.academy.it.travelcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SIGHTSEEING")
public class Sightseeing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SIGHTSEEING_ID", unique = true)
    private Long sightseeingId;
    @Column(name = "NAME")
    private String sightseeingName;
    @OneToMany(mappedBy = "sightseeing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TravelPoint> travelPoints;
}
