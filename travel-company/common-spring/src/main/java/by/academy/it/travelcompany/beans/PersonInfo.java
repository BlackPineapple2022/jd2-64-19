package by.academy.it.travelcompany.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {

    private Long id;
    private Person person;
    private Long salary;
    private LocalDate dayOfBirth;
    private LocalDate dayOfJoinCompany;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id=" + id +
                ", salary=" + salary +
                ", dayOfBirth=" + dayOfBirth +
                ", dayOfJoinCompany=" + dayOfJoinCompany +
                '}';
    }
}
