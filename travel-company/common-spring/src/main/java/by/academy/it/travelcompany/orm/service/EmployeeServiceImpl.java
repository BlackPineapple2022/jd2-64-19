package by.academy.it.travelcompany.orm.service;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.dao.impl.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl {

    @Autowired
    @Qualifier(value = "employeeDAO")
    private EmployeeDAO employeeDAO;

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void sayHelloAndSayEmployeeDao(){
        /*System.out.println("Hello!");
        employeeDAO.sayHello();*/

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.academy.it");
        Object employeeDAOImpl = context.getBean("employeeDAO");
        ((EmployeeDAO)employeeDAOImpl).sayHello();

    }
}
