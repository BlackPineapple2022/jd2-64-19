package by.academy.it.travelcompany.orm.repository;

import by.academy.it.travelcompany.orm.entity.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface MeetingRepository extends CrudRepository<Meeting, Serializable> {

}
