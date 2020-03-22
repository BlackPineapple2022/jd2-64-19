package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;

public interface FlightJournalService {

    void create(FlightJournal flightJournal);

    FlightJournal getByRouteMap(RouteMap routeMap);

    RouteMap getRouteMapByOlderOrNullableFlightJournal(String airlineName);

}
