package by.academy.it.travelcompany.orm.repository;

import by.academy.it.travelcompany.orm.entity.Department;
import by.academy.it.travelcompany.orm.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface DepartamentRepository extends CrudRepository<Department, Serializable> {

    Department findByDepartmentName(String name);

    Department findByEmployeeListContains(Employee employee);

}
