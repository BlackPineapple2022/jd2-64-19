package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.routemap.RouteMap;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface RouteMapDAO extends DAO<RouteMap> {

    Optional<RouteMap> getRouteMapByParam(String airline, String originAirport, String destinationAirport, String direction) throws SQLException;

    Set<RouteMap> getRouteMapSetByAirportCodeSets(Set <String> originDirect, Set <String>destinationDirect, Set<String> destinationReturn, Set<String> originReturn) throws SQLException;
}
