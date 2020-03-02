package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl extends AbstractDAO<Employee> implements EmployeeDAO {

    public EmployeeDAOImpl() {
        super(Employee.class);
    }


}