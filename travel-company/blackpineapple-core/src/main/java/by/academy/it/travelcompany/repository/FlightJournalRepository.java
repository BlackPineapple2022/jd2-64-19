package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface FlightJournalRepository extends CrudRepository<FlightJournal, Serializable> {

        FlightJournal getByRouteMap(RouteMap routeMap);

        List<FlightJournal> getAllByRouteMap_Airline_NameOrderByUpdatedDateTime(String airlineName);

}

