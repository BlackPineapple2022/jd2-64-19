package by.academy.it.travelcompany.entity.homework.orm;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table (name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "EMPLOYEE_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long id;
    private String firstName;
    private String lastName;

    private Double salary;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeDetail employeeDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_MEETING",joinColumns = { @JoinColumn(name = "EMPLOYEE_ID")}, inverseJoinColumns = {@JoinColumn(name="MEETING_ID")})
    private List<Meeting> meetingList = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(employeeDetail, employee.employeeDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, employeeDetail);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeDetail=" + employeeDetail +
                ", department=" + department +
                '}';
    }
}
