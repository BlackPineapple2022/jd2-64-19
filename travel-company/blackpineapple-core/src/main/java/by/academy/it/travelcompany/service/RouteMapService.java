package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.RouteMap;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RouteMapService {

    void create(RouteMap routeMap);

    List<RouteMap> getAll();

    Optional<RouteMap> getById(Serializable id);

    RouteMap getByRouteMapString(String routeMapString);

}
