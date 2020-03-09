package by.academy.it.travelcompany.orm.entity;

import by.academy.it.serializer.LocalDateDeserializer;
import by.academy.it.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"meetingList"})
@Entity
@Table(name = "EMPLOYEE")
@EqualsAndHashCode(exclude = {"id","employeeDetail","department","meetingList"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;
    private String firstName;
    private String lastName;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @CreationTimestamp
    private LocalDate joinDate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EmployeeDetail employeeDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Access(AccessType.PROPERTY)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Meeting> meetingList = new ArrayList<>();

}
