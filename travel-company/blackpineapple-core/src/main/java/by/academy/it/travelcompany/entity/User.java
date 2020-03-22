package by.academy.it.travelcompany.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id","password","salt","role","favourite"})
@ToString
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_SALT")
    private String salt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Favourite favourite;

    public User(String name,String password,Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
