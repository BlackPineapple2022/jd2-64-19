package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"userList","id"})
@ToString(exclude = {"userList"})
@Table(name = "ROLE")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;
    @Column(name = "ROLE_NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> userList;

    public Role(String name) {
        this.name = name;
    }
}
