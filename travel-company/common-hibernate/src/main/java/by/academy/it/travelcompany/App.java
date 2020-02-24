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
import java.util.TimeZone;

@Slf4j
public class App {
    public static void main(String[] args) {

        Department department1 = new Department(null, "QA", new ArrayList<>());
        Department department2 = new Department(null, "DEV", new ArrayList<>());
        Department department3 = new Department(null, "HR", new ArrayList<>());

        Employee employee1 = new Employee(null, "Ksenia", "Sobchak", 1000.0, null, department1, null);
        Employee employee2 = new Employee(null, "Jack", "Vorobey", 2000.0, null, department2, null);
        Employee employee3 = new Employee(null, "Ivan", "Ivanov", 3000.0, null, department3, null);
        Employee employee4 = new Employee(null, "I have No lastName", null, 3000.0, null, department3, null);
        Employee employee5 = new Employee(null, "Ilon", "Mask", 75000.0, null, department1, null);

        EmployeeDetail employeeDetail1 = new EmployeeDetail(null, "St. Petersburg", LocalDate.now(), LocalDate.of(1981, 11, 5), employee1);
        EmployeeDetail employeeDetail2 = new EmployeeDetail(null, "St. Petersburg", LocalDate.now(), LocalDate.of(1911, 12, 19), employee2);
        EmployeeDetail employeeDetail3 = new EmployeeDetail(null, "St. Petersburg", LocalDate.now(), LocalDate.of(1991, 5, 5), employee3);
        EmployeeDetail employeeDetail4 = new EmployeeDetail(null, "St. Petersburg", LocalDate.now(), LocalDate.of(1992, 7, 7), employee4);

        department1.getEmployeeList().add(employee1);
        department1.getEmployeeList().add(employee5);
        department2.getEmployeeList().add(employee2);
        department3.getEmployeeList().add(employee3);

        employee1.setEmployeeDetail(employeeDetail1);
        employee2.setEmployeeDetail(employeeDetail2);
        employee3.setEmployeeDetail(employeeDetail3);
        employee4.setEmployeeDetail(employeeDetail4);

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

        List<Employee> all = employeeDAO.getAll();
        log.info("ALL: " + all);
        System.err.println("ALL: " + all);

        List<Employee> allWithSameName = employeeDAO.getByName("Jack", "Vorobey");
        log.info("JACK VOROBEY: " + allWithSameName);
        System.err.println("JACK VOROBEY: " + allWithSameName);

        List<Employee> allWithNameNotNull = employeeDAO.getAllWithNameNotNull();
        log.info("ALL NOT NULL FIRST NAME AND LAST NAME: " + allWithNameNotNull);
        System.err.println("ALL NOT NULL FIRST NAME AND LAST NAME: " + allWithNameNotNull);

        List<Employee> allWithSalaryGT1001 = employeeDAO.getSalaryGraterThan(1001L);
        log.info("ALL SALARY > 1001: " + allWithSalaryGT1001);
        System.err.println("ALL SALARY > 1001: " + allWithSalaryGT1001);

        List<Employee> allWithSalaryGT1001Desc = employeeDAO.getSalaryGraterThanOrderDesc(1001L);
        log.info("ALL SALARY > 1001 order DESC: " + allWithSalaryGT1001Desc);
        System.err.println("ALL SALARY > 1001 order DESC: " + allWithSalaryGT1001Desc);

        List<Employee> allWithSalaryLTorEQ1000 = employeeDAO.getSalaryLessOrEqual(1000L);
        log.info("ALL SALARY <= 1000: " + allWithSalaryLTorEQ1000);
        System.err.println("ALL SALARY <= 1000: " + allWithSalaryLTorEQ1000);

        List<Employee> allOlder30AndYounger80 = employeeDAO.getByAgeBetween(30, 1000);
        log.info("ALL OLDER 30 AND YOUNGER 80: " + allOlder30AndYounger80);
        System.err.println("ALL OLDER 30 AND YOUNGER 80 : " + allOlder30AndYounger80);

        List<Employee> byNameAndAge = employeeDAO.getByAgeAndName("Ksenia", "Sobchak", 38);
        log.info("Ksenia Sochak 38: " + byNameAndAge);
        System.err.println("Ksenia Sobchak 38: " + byNameAndAge);

        List<Employee> byNameOrAge = employeeDAO.getByAgeOrName("Ksenia", " ", 28);
        log.info("28 Age or Name Ksenia: " + byNameOrAge);
        System.err.println("28 Age or Name Ksenia: " + byNameOrAge);

        System.err.println("Employee count: " + employeeDAO.getEmployeeCount());
        log.info("Employee count: " + employeeDAO.getEmployeeCount());
        System.err.println("Employee average salary: " + employeeDAO.getAverageSalary());
        log.info("Employee average salary: " + employeeDAO.getAverageSalary());
        System.err.println("Employee max salary: " + employeeDAO.getMaxSalary());
        log.info("Employee max salary: " + employeeDAO.getMaxSalary());
        System.err.println("Employee min age: " + employeeDAO.getMinAge());
        log.info("Employee min age: " + employeeDAO.getMinAge());
        System.err.println("average salary on dep with id=1: "+employeeDAO.getAverageSalaryByDep(1L));
        log.info("average salary on dep with id=1: "+employeeDAO.getAverageSalaryByDep(1L));


    }
}
