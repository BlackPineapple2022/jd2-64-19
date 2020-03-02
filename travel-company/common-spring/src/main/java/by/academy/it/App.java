package by.academy.it;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class App {

    @Autowired
    private EmployeeDAO employeeDAO;

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setFirstName("Vova");

        App app = new App();
        app.employeeDAO.add(employee);
    }





}



