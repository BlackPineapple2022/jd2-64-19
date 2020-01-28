package by.academy.it.travelcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRAVEL_POINT_DETAIL")
public class TravelPointDetail {

    @Id
    @GenericGenerator(name = "one-one",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "travelPoint"))
    @GeneratedValue(generator="one-one")
    @Column(name = "TRAVEL_POINT_ID")
    @Access(AccessType.PROPERTY)
    private Long id;

    private String country;
    private String city;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    private TravelPoint travelPoint;
}
