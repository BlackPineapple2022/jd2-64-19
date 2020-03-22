package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.entity.RouteMap;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RouteMapService {

    void create(RouteMap routeMap);

    List<RouteMap> getAll();

    Optional<RouteMap> getById(Serializable id);

    RouteMap getByRouteMapString(String routeMapString);

    Set<RouteMap> getRouteMapSetByAirportCodeSets
            (Set<String> originAirportDirectCodeSet,
             Set<String> destinationAirportDirectCodeSet,
             Set<String> destinationAirportReturnCodeSet,
             Set<String>originAirportReturnCodeSet);

}
