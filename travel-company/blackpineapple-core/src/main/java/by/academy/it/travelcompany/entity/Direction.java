package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(exclude = {"id","routeMapList"})
@ToString(exclude = {"routeMapList"})
@Table(name = "DIRECTION")
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIRECTION_ID")
    private Long id;
    @Column(name = "DIRECTION_NAME")
    private String name;

    @OneToMany(mappedBy = "direction",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RouteMap> routeMapList;

    public Direction(String name) {
        this.name = name;
    }
}
