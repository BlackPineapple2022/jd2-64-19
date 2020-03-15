package by.academy.it.travelcompany.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name ="FAVOURITE")
@EqualsAndHashCode (exclude = {"id","roundTripList"})
@ToString
@Data
public class Favourite {
    @Id
    @Column(name = "FAVOURITE_ID")
    @GenericGenerator(name = "one-one-favourite", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @GeneratedValue(generator = "one-one-favourite")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToMany
    @JoinTable(name = "FAVOURITE_ROUND_TRIP",
            joinColumns = {@JoinColumn(name = "FAVOURITE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROUND_TRIP_ID")})
    private List<RoundTrip> roundTripList;

    public Favourite(User user) {
        this.user = user;
    }
}
