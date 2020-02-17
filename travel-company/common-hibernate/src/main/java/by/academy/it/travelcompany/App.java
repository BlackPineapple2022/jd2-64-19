package by.academy.it.travelcompany;

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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //ONE TO ONE RELATIONS

        Employee employee = new Employee(null,"Vladiko", "Dart Veider", null, null,null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null,"Tatuin", LocalDate.now(), LocalDate.of(1989,4,3),employee);
        employee.setEmployeeDetail(employeeDetail);

        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        session.close();

        //ONE TO MANY RELATIONS
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        employee = new Employee(null,"Jedi Knight", "Luk Skywalker", null, null,null);
        employeeDetail = new EmployeeDetail(null,"Korusant", LocalDate.now(), LocalDate.of(1989,4,3),employee);
        employee.setEmployeeDetail(employeeDetail);

        Department department = new Department(null,"Jedi",new ArrayList<>());
        employee.setDepartment(department);
        department.getEmployeeList().add(employee);

        session.saveOrUpdate(employee);
        session.saveOrUpdate(department);
        session.getTransaction().commit();
        session.close();

        //MANY TO MANY

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Employee employee1 = session.find(Employee.class,1L);
        log.debug("employee 1 from DB : "+employee1);
        Employee employee2 = session.find(Employee.class,2L);
        log.debug("employee 2 from DB : "+employee2);

        employee = new Employee(null,"Witcher", "Gerald from Rivia", null, null,new ArrayList<>());
        employeeDetail = new EmployeeDetail(null,"Rivia", LocalDate.now(), LocalDate.of(1989,4,3),employee);
        employee.setEmployeeDetail(employeeDetail);

        department = new Department(null,"Witcher guild",new ArrayList<>());
        employee.setDepartment(department);
        department.getEmployeeList().add(employee);

        Meeting firstMeeting = new Meeting(null,"Stars Witchers",new ArrayList<>());
        firstMeeting.getEmployeeList().add(employee);
        employee.getMeetingList().add(firstMeeting);
        firstMeeting.getEmployeeList().add(employee1);
        employee1.getMeetingList().add(firstMeeting);
        firstMeeting.getEmployeeList().add(employee2);
        employee2.getMeetingList().add(firstMeeting);

        Meeting secondMeeting = new Meeting(null,"Veider-Gerald",new ArrayList<>());
        secondMeeting.getEmployeeList().add(employee);
        employee.getMeetingList().add(secondMeeting);
        secondMeeting.getEmployeeList().add(employee2);
        employee2.getMeetingList().add(secondMeeting);

        session.saveOrUpdate(employee);
        session.saveOrUpdate(employee1);
        session.saveOrUpdate(employee2);
        session.saveOrUpdate(department);
        session.saveOrUpdate(firstMeeting);
        session.saveOrUpdate(secondMeeting);
        session.getTransaction().commit();
        session.close();

        //HQL HOMEWORK

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Employee> selectFromEmployee = session.createQuery("from Employee", Employee.class);
        List<Employee> employeeList = selectFromEmployee.list();
        log.debug("Employees in DB : "+employeeList);

        Query<String> selectByAddress = session.createQuery("select e.lastName from Employee as e where e.employeeDetail.address = 'Tatuin'", String.class);
        List <String> employeesLastNames = selectByAddress.list();
        log.debug("Employees from Tatuin : "+employeesLastNames);

        Query<Employee> selectByDepartment = session.createQuery("select e from Employee as e where e.department.departmentName = 'Witcher guild'", Employee.class);
        List <Employee> employees = selectByDepartment.list();
        log.debug("Employees from Witcher guild: "+employees);


        Query<String> selectAllDepartmentName = session.createQuery("select d.departmentName from Department as d", String.class);
        List<String> departmentNames = selectAllDepartmentName.list();


        for (String s: departmentNames ) {
            Query<Long> selectCountOfEmployeeInDepartment = session.createQuery("select count(e) from Employee e where department.departmentName = :name",Long.class);
            selectCountOfEmployeeInDepartment.setParameter("name",s);
            log.debug("In department: " + s + " count of employee: "+selectCountOfEmployeeInDepartment.list().get(0));
        }

        Department department1 = new Department(null,"Siths",new ArrayList<>());
        session.saveOrUpdate(department1);
        session.getTransaction().commit();

        session.beginTransaction();
        Query updateEmployyeDepartment = session.createQuery("update Employee e set e.department.id =: newDepartmentId where e.lastName =: lastName");

        department1 = session.find(Department.class,3L);
        updateEmployyeDepartment.setParameter("newDepartmentId", department1.getDepartmentId());
        updateEmployyeDepartment.setParameter("lastName", "Dart Veider");
        updateEmployyeDepartment.executeUpdate();

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
