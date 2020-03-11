package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id","routeMapList"})
@ToString(exclude = {"routeMapList"})
@Entity
@Table(name="AIRLINE")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRLINE_ID")
    private Long id;
    @Column(name = "AIRLINE_NAME")
    private String name;

    @OneToMany(mappedBy = "airline",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RouteMap> routeMapList = new ArrayList<>();

    public Airline(String name) {
        this.name = name;
    }
}
