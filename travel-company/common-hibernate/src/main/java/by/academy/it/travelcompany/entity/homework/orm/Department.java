package by.academy.it.travelcompany.entity.homework.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", unique = true)
    private Long departmentId;
    private String departmentName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    @Override
    public String toString() {
        return "Department{" +
                "DepartmentName='" + departmentName + '\'' +
                '}';
    }
}
