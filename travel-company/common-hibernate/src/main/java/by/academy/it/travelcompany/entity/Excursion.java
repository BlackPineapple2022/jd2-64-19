package by.academy.it.travelcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXCURSION")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXCURSION_ID")
    Long excursionId;

    private String excursionName;

    @ManyToMany (mappedBy = "excursions")
    private List<TravelPoint> travelPoints = new ArrayList<>();

}
