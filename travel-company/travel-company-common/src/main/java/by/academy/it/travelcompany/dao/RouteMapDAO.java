package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.routemap.RouteMap;

import java.sql.SQLException;
import java.util.Optional;

public interface RouteMapDAO extends DAO<RouteMap> {

    Optional<RouteMap> getRouteMapByParam(String airline, String originAirport, String destinationAirport, String direction) throws SQLException;
}
