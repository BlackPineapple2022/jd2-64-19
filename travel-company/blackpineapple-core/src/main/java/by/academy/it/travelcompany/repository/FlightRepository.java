package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface FlightRepository extends CrudRepository<Flight, Serializable> {
}
