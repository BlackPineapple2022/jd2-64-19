package by.academy.it.travelcompany.DAO;

import by.academy.it.travelcompany.entity.homework.orm.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAll();

    List<Employee> getByName(String firstName, String lastName);

    List<Employee> getAllWithNameNotNull();

    List<Employee> getSalaryGraterThan(Long salary);

    List<Employee> getSalaryGraterThanOrderDesc(Long salary);

    List<Employee> getSalaryLessOrEqual(Long salary);

    List<Employee> getByAgeBetween(Integer from, Integer to);

    List<Employee> getByAgeAndName(String firstName, String lastName,  Integer age);

    List<Employee> getByAgeOrName(String firstName, String lastName,  Integer age);

    // aggregation

    long getEmployeeCount();

    Double getAverageSalary();

    Double getMaxSalary();

    long getMinAge();

    Double getAverageSalaryByDep(Long depId);

}
