package by.academy.it;

import by.academy.it.travelcompany.orm.dao.EmployeeDAO;
import by.academy.it.travelcompany.orm.dao.impl.EmployeeDAOImpl;
import by.academy.it.travelcompany.orm.entity.Employee;
import by.academy.it.travelcompany.orm.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import sun.applet.Main;

@Slf4j
@Component
public class App {

    @Autowired
    private EmployeeServiceImpl employeeService;

    public static void main(String[] args) {
        //EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
  /*      employeeService.sayHelloAndSayEmployeeDao();*/

    }

    public void getGetGet(){
        System.out.println(employeeService);
    }





}



