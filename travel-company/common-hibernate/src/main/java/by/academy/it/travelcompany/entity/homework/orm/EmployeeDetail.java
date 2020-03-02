package by.academy.it.travelcompany.entity.homework.orm;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE_DETAIL")
public class EmployeeDetail {
    @Id
    @GenericGenerator(name = "one-one", strategy = "foreign", parameters = @Parameter(name="property", value = "employee"))
    @GeneratedValue(generator = "one-one")
    @Column (name = "EMPLOYEE_ID") @Access(AccessType.PROPERTY)
    private Long id;
    private String address;
    @Column
    private LocalDate entranceLocalDate;
    @Column
    private LocalDate birthdayLocalDate;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDetail that = (EmployeeDetail) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(entranceLocalDate, that.entranceLocalDate) &&
                Objects.equals(birthdayLocalDate, that.birthdayLocalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, entranceLocalDate, birthdayLocalDate);
    }

    @Override
    public String toString() {
        return "EmployeeDetail{" +
                "address='" + address + '\'' +
                ", entranceLocalDate=" + entranceLocalDate +
                ", birthdayLocalDate=" + birthdayLocalDate +
                '}';
    }
}
