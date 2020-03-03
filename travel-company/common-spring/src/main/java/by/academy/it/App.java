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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
public class App {

    public static void main(String[] args) {

        Department dev = new Department(null,"developer",new ArrayList<>());
        Department qa = new Department(null,"qa",new ArrayList<>());

        Employee vova = new Employee(null,"Vova","Dubovskiy", LocalDate.now(),null,dev,new ArrayList<>());
        EmployeeDetail vovaDetail = new EmployeeDetail(null,"Belarus","Minsk","Puskina sq",LocalDate.of(1989,4,3),null);

        Employee ivan = new Employee(null,"Ivan","Grozniy", LocalDate.now(),null,qa,new ArrayList<>());
        EmployeeDetail ivanDetail = new EmployeeDetail(null,"Russia","Moscow","",LocalDate.of(1530,8,25),null);

        Employee petr = new Employee(null,"Petr","Perviy",LocalDate.now(),null,qa,new ArrayList<>());
        EmployeeDetail petrDetail = new EmployeeDetail(null,"Russia","St.Petersburg","",LocalDate.of(1672,5,30),null);

        Meeting firstConference = new Meeting(null,"Introduction into Spring-orm and Spring DATA JPA", LocalDateTime.now().plusDays(1),new ArrayList<>());
        Meeting secondConference = new Meeting(null,"Russian private King festival", LocalDateTime.now().plusDays(7),new ArrayList<>());

        dev.getEmployeeList().add(vova);
        qa.getEmployeeList().add(ivan);
        qa.getEmployeeList().add(petr);

        vova.getMeetingList().add(firstConference);
        firstConference.getEmployeeList().add(vova);

        ivan.getMeetingList().add(firstConference);
        ivan.getMeetingList().add(secondConference);
        firstConference.getEmployeeList().add(ivan);
        secondConference.getEmployeeList().add(ivan);

        petr.getMeetingList().add(secondConference);
        petr.getMeetingList().add(secondConference);
        firstConference.getEmployeeList().add(petr);
        secondConference.getEmployeeList().add(petr);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        EmployeeService employeeService = context.getBean(EmployeeService.class);
        EmployeeDetailService employeeDetailService = context.getBean(EmployeeDetailService.class);
        DepartamentRepository departmentRepository = context.getBean(DepartamentRepository.class);
        MeetingRepository meetingRepository = context.getBean(MeetingRepository.class);

        employeeService.add(vova);
        employeeService.add(ivan);
        employeeService.add(petr);

        Employee vovaFromDB = employeeService.get(1L);
        Serializable vovaId = vovaFromDB.getId();
        vovaDetail.setId((Long)vovaId);
        employeeDetailService.add(vovaDetail);

        Employee ivanFromDB = employeeService.get(2L);
        Serializable ivanId = ivanFromDB.getId();
        ivanDetail.setId((Long)ivanId);
        employeeDetailService.add(ivanDetail);

        Employee petrFromDB = employeeService.get(3L);
        Serializable petrId = petrFromDB.getId();
        petrDetail.setId((Long)petrId);
        employeeDetailService.add(petrDetail);


        Meeting thirdMeeting = new Meeting(null,"Pineapple big conference",LocalDateTime.now().plusDays(12),new ArrayList<>());
        Employee newVovaFromDB = employeeService.get(1L);
        thirdMeeting.getEmployeeList().add(newVovaFromDB);
        newVovaFromDB.getMeetingList().add(thirdMeeting);
        employeeService.update(newVovaFromDB);

        Iterable<Meeting> meetingIterable= meetingRepository.findAll();
        for (Meeting meeting : meetingIterable) {
           log.info(""+meeting);
        }


    }






}



