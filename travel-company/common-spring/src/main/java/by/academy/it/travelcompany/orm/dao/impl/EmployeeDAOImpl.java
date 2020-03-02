package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("employeeDAO")
public class EmployeeDAOImpl  /*extendsAbstractDAO<Employee>*/ implements EmployeeDAO {

    /*public EmployeeDAOImpl() {
        super(Employee.class);
    }*/

    @Override
    public void sayHello() {
        System.out.println("Hello from employee dao!!!");
    }


}