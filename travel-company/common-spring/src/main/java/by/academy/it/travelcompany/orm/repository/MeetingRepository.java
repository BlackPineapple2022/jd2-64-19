package by.academy.it.travelcompany.orm.repository;

import by.academy.it.travelcompany.orm.entity.Employee;
import by.academy.it.travelcompany.orm.entity.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Serializable> {

    List<Meeting> getAllByMeetingSubjEquals(String subj);

    List<Meeting> getAllByMeetingDateTimeAfter(LocalDateTime localDateTime);

    List<Meeting> getAllByMeetingDateTimeBefore(LocalDateTime localDateTime);

    List<Meeting> getAllByMeetingDateTimeBetween(LocalDateTime from, LocalDateTime to);

    List<Meeting> getAllByEmployeeListContains(Employee employee);

}
