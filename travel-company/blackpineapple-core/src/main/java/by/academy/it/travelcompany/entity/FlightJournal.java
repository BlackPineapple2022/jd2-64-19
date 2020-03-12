package by.academy.it.travelcompany.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "FLIGHT_JOURNAL")
public class FlightJournal {
    @Id
    @Column(name = "FLIGHT_JOURNAL_ID")
    @GenericGenerator(name = "one-one", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "routeMap"))
    @GeneratedValue(generator = "one-one")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private RouteMap routeMap;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;

    public FlightJournal(RouteMap routeMap,LocalDateTime updatedDateTime) {
        this.routeMap = routeMap;
        this.updatedDateTime = updatedDateTime;
    }
}
