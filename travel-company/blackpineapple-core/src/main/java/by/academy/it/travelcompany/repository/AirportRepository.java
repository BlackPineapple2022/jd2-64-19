package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Airport;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AirportRepository extends CrudRepository<Airport, Serializable> {

    Airport getByCode(String code);
}
