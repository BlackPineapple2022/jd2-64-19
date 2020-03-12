package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.FlightJournal;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface FlightJournalRepository extends CrudRepository<FlightJournal, Serializable> {


}

