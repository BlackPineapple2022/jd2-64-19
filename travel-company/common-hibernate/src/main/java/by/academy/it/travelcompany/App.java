package by.academy.it.travelcompany;

import by.academy.it.travelcompany.DAO.EmployeeDAO;
import by.academy.it.travelcompany.DAO.impl.EmployeeDAOImpl;
import by.academy.it.travelcompany.entity.homework.orm.Department;
import by.academy.it.travelcompany.entity.homework.orm.Employee;
import by.academy.it.travelcompany.entity.homework.orm.EmployeeDetail;
import by.academy.it.travelcompany.entity.homework.orm.Meeting;
import by.academy.it.travelcompany.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {

        Department department1 = new Department(null,"QA",new ArrayList<>());
        Department department2 = new Department(null,"DEV",new ArrayList<>());
        Department department3 = new Department(null,"HR",new ArrayList<>());

        Employee employee1 = new Employee(null,"Ksenia","Sobchak",1000.0,null,department1,null);
        Employee employee2 = new Employee(null,"Jack","Vorobey",2000.0,null,department2,null);
        Employee employee3 = new Employee(null,"Ivan","Ivanov",3000.0,null,department3,null);
        Employee employee4 = new Employee(null,"I have No lastName",null,3000.0,null,department3,null);

        department1.getEmployeeList().add(employee1);
        department2.getEmployeeList().add(employee2);
        department3.getEmployeeList().add(employee3);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employee1);
        session.save(employee2);
        session.save(employee3);
        session.save(employee4);
        session.save(department1);
        session.save(department2);
        session.save(department3);
        session.getTransaction().commit();
        session.close();

        EmployeeDAO employeeDAO = EmployeeDAOImpl.getINSTANCE();
        List<Employee> allWithNameNotNull = employeeDAO.getAllWithNameNotNull();


    }
}
