package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"id","flightList"})
@EqualsAndHashCode(exclude = {"id","flightList"})
@Entity
@Table(name = "CURRENCY")
public class Currency {
    @Column(name = "CURRENCY_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CURRENCY_CODE")
    private String code;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Flight> flightList;

    public Currency(String code) {
        this.code = code;
    }
}
