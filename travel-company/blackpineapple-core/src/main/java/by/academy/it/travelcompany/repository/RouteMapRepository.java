package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.RouteMap;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface RouteMapRepository extends CrudRepository<RouteMap, Serializable> {

    RouteMap findByAirline_NameAndAirportOrigin_CodeAndAirportDestination_Code(String airlineName, String originCode, String destinationCode);

}
