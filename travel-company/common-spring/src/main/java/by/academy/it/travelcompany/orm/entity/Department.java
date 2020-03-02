package by.academy.it.travelcompany.orm.entity;

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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="DEPARTMENT_ID")
    private Long id;
    private String departmentName;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "department")
    @Access(AccessType.PROPERTY)
    private List<Employee> employeeList = new ArrayList<>();
}
