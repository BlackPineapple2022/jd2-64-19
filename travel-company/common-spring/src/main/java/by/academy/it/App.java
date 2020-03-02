package by.academy.it;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.dao.impl.EmployeeDAOImpl;
import by.academy.it.travelcompany.orm.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App {

   /* @Autowired
    private EmployeeDAO employeeDAO;*/

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("by.academy.it");
        /*EmployeeDAO employeeDAO = (EmployeeDAO)context.getBean("employeeDAO");

        Employee employee = new Employee();
        employee.setFirstName("Vova");

        employeeDAO.add(employee);*/


    }





}



