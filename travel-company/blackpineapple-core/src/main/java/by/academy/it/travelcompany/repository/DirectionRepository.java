package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Direction;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface DirectionRepository extends CrudRepository<Direction, Serializable> {

    Direction getByName(String name);

}
