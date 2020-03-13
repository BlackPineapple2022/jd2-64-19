package by.academy.it;

import by.academy.it.travelcompany.orm.entity.Department;
import by.academy.it.travelcompany.orm.entity.Employee;
import by.academy.it.travelcompany.orm.entity.EmployeeDetail;
import by.academy.it.travelcompany.orm.entity.Meeting;
import by.academy.it.travelcompany.orm.repository.DepartamentRepository;
import by.academy.it.travelcompany.orm.repository.MeetingRepository;
import by.academy.it.travelcompany.orm.service.EmployeeDetailService;
import by.academy.it.travelcompany.orm.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
public class InitializationParam {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        EmployeeService employeeService = context.getBean(EmployeeService.class);
        EmployeeDetailService employeeDetailService = context.getBean(EmployeeDetailService.class);
        DepartamentRepository departamentRepository = context.getBean(DepartamentRepository.class);
        MeetingRepository meetingRepository = context.getBean(MeetingRepository.class);

        Department qa = new Department(null, "qa", new ArrayList<>());
        Department dev = new Department(null, "dev", new ArrayList<>());

        EmployeeDetail employeeDetail1 = new EmployeeDetail(null, "1c", "1ci", "1str", LocalDate.of(2001, 1, 1), null);
        Employee employee1 = new Employee(null, "1f", "1l", LocalDate.now(), employeeDetail1, qa, new ArrayList<>());
        employeeDetail1.setEmployee(employee1);

        EmployeeDetail employeeDetail2 = new EmployeeDetail(null, "2c", "2ci", "2str", LocalDate.of(2002, 2, 2), null);
        Employee employee2 = new Employee(null, "2f", "2l", LocalDate.now(), employeeDetail2, dev, new ArrayList<>());
        employeeDetail2.setEmployee(employee2);

        EmployeeDetail employeeDetail3 = new EmployeeDetail(null, "3c", "3ci", "3str", LocalDate.of(2003, 3, 3), null);
        Employee employee3 = new Employee(null, "3f", "3l", LocalDate.now(), employeeDetail3, dev, new ArrayList<>());
        employeeDetail3.setEmployee(employee3);

        dev.getEmployeeList().add(employee2);
        dev.getEmployeeList().add(employee3);
        qa.getEmployeeList().add(employee1);

        departamentRepository.save(qa);
        departamentRepository.save(dev);

        Meeting m1 = new Meeting(null, "m1", LocalDateTime.now(), new ArrayList<>());
        meetingRepository.save(m1);

        Employee employee1DB = employeeService.get(1L);
        employee1DB.getMeetingList().add(m1);
        Employee employee2DB = employeeService.get(2L);
        employee2DB.getMeetingList().add(m1);
        Employee employee3DB = employeeService.get(3L);
        employee3DB.getMeetingList().add(m1);

        employeeService.update(employee1DB);
        employeeService.update(employee2DB);
        employeeService.update(employee3DB);

        ((ClassPathXmlApplicationContext) context).close();

    }

}



