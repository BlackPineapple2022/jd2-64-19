package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "FLIGHT_JOURNAL")
public class FlightJournal {
    @Id
    @Column(name = "FLIGHT_JOURNAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RouteMap routeMap;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;
}
