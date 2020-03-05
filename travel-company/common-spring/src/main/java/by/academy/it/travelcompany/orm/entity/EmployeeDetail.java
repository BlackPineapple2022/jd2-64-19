package by.academy.it.travelcompany.orm.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"employee"})
@Entity
@Table(name= "EMPLOYEE_DETAIL")
@EqualsAndHashCode(exclude = {"employee"})
public class EmployeeDetail {
    @Id
    @GenericGenerator(name = "one-one", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
    @Column(name = "EMPLOYEE_DETAIL_ID")
    private Long id;
    private String country;
    private String city;
    private String street;
    private LocalDate dateOfBirthDay;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    private Employee employee;
}
