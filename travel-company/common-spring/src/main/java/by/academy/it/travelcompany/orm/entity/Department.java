package by.academy.it.travelcompany.orm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employeeList"})
@EqualsAndHashCode(exclude = {"employeeList","id"})
@Table(name="DEPARTMENT")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="DEPARTMENT_ID")
    private Long id;

    private String departmentName;
    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();
}
