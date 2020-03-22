package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Airline;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AirlineRepository extends CrudRepository<Airline, Serializable> {

    Airline getByName(String name);

}
