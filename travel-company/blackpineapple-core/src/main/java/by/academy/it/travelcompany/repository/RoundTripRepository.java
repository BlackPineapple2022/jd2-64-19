package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.RoundTrip;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface RoundTripRepository extends CrudRepository<RoundTrip, Serializable> {
}
