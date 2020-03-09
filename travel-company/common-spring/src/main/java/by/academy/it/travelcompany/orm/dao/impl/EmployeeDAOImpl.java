package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.DAO;
import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class EmployeeDAOImpl extends AbstractDAO<Employee> implements EmployeeDAO {

    public EmployeeDAOImpl() {
        super(Employee.class);
    }




}