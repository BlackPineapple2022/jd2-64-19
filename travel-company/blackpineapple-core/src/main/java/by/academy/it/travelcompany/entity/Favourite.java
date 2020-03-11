package by.academy.it.travelcompany.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="FAVOURITE")
@EqualsAndHashCode
@ToString
@Data
public class Favourite {
    @Id
    @Column(name = "FAVOURITE_ID")
    @GenericGenerator(name = "one-one", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @GeneratedValue(generator = "one-one")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany
    @JoinTable(name = "FAVOURITE_ROUND_TRIP",
            joinColumns = {@JoinColumn(name = "FAVOURITE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROUND_TRIP_ID")})
    private List<RoundTrip> roundTripList;

}
