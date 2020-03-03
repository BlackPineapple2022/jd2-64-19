package by.academy.it.travelcompany.orm.service.impl;

import by.academy.it.travelcompany.orm.entity.Employee;
import by.academy.it.travelcompany.orm.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractCRUDService<Employee> implements EmployeeService {

    protected EmployeeServiceImpl() {
        super(Employee.class);
    }
}
