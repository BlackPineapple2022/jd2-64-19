package by.academy.it.travelcompany.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"employee"})
@Entity
public class EmployeeDetail {
    @Id
    @GenericGenerator(name = "one-one", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
    private Long id;
    private String country;
    private String city;
    private String street;
    private LocalDate dateOfBirthDay;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Employee employee;
}
