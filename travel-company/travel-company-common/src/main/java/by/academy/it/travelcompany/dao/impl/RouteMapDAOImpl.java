package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.direction.Direction;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

@Slf4j
public class RouteMapDAOImpl extends AbstractDAO implements RouteMapDAO {

    private static final RouteMapDAO INSTANCE = new RouteMapDAOImpl();

    private RouteMapDAOImpl() {
        super(LoggerFactory.getLogger(RouteMapDAOImpl.class));
    }

    public static RouteMapDAO getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_ROUTEMAP = "INSERT INTO routemap (airline_id, origin_airport_id, destination_airport_id,direction_id ) VALUE (?,?,?,?)";
    private static final String SELECT_ROUTEMAP = "SELECT r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name" +
            " FROM routemap r JOIN airline al ON r.airline_id = al.id JOIN airport apo ON r.origin_airport_id = apo.id JOIN airport apd ON r.destination_airport_id = apd.id JOIN direction d on r.direction_id=d.id WHERE r.id=?";
    private static final String UPDATE_ROUTEMAP = "UPDATE routemap SET airline_id = ?, origin_airport_id = ?, destination_airport_id = ?, direction_id = ? WHERE id = ?";
    private static final String DELETE_ROUTEMAP = "DELETE FROM routemap WHERE id = ?";

    private static final String SELECT_ALL_ROUTEMAP = "SELECT r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name" +
            " FROM routemap r JOIN airline al ON r.airline_id = al.id JOIN airport apo ON r.origin_airport_id = apo.id JOIN airport apd ON r.destination_airport_id = apd.id JOIN direction d on r.direction_id=d.id ORDER BY r.id ASC";
    private static final String SELECT_ROUTEMAP_BY_PARAMETERS = "SELECT r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name" +
            " FROM routemap r JOIN airline al ON r.airline_id = al.id JOIN airport apo ON r.origin_airport_id = apo.id JOIN airport apd ON r.destination_airport_id = apd.id JOIN direction d on r.direction_id=d.id WHERE al.airline_name = ? AND apo.airport_code=? AND apd.airport_code = ? AND d.direction_name = ?";
    private static final String SELECT_ALL_ROUTEMAP_BY_ORIGIN_AND_DESTINATION = "SELECT r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name" +
            " FROM routemap r JOIN airline al ON r.airline_id = al.id JOIN airport apo ON r.origin_airport_id = apo.id JOIN airport apd ON r.destination_airport_id = apd.id JOIN direction d on r.direction_id=d.id WHERE apo.airport_code=? AND apd.airport_code = ?";

//CRUD

    @Override
    public Long create(RouteMap r) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ROUTEMAP, Statement.RETURN_GENERATED_KEYS)) {

            Long airlineId = r.getAirline().getId();
            Long originAirportId = r.getOriginAirport().getId();
            Long destinationAirportId = r.getDestinationAirport().getId();
            Long directionId = r.getDirection().getId();

            statement.setLong(1, airlineId);
            statement.setLong(2, originAirportId);
            statement.setLong(3, destinationAirportId);
            statement.setLong(4, directionId);

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional<RouteMap> read(Long id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ROUTEMAP)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RouteMap result = mapRouteMap(resultSet);
                return Optional.of(result);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public int update(RouteMap routeMap) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROUTEMAP)) {

            statement.setLong(1, routeMap.getAirline().getId());
            statement.setLong(2, routeMap.getOriginAirport().getId());
            statement.setLong(3, routeMap.getDestinationAirport().getId());
            statement.setLong(4, routeMap.getDirection().getId());
            statement.setLong(5, routeMap.getId());

            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ROUTEMAP)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

//!CRUD

    @Override
    public List<RouteMap> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<RouteMap> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ROUTEMAP)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(mapRouteMap(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional <RouteMap> getByParam(String airline, String originAirport, String destinationAirport, String direction) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ROUTEMAP_BY_PARAMETERS)) {
            statement.setString(1,airline);
            statement.setString(2,originAirport);
            statement.setString(3,destinationAirport);
            statement.setString(4,direction);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapRouteMap(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return Optional.empty();
    }

    public Set<RouteMap> getRouteMapSetByAirportCodeSets(Set <String> originDirect, Set <String>destinationDirect, Set<String> destinationReturn, Set<String> originReturn) throws SQLException{
        Set<RouteMap> routeMapSet = new HashSet<>();
        for (String originDirectStr: originDirect) {
            for (String destinationDirectStr: destinationDirect ) {
                ResultSet resultSet = null;
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ROUTEMAP_BY_ORIGIN_AND_DESTINATION)) {
                    statement.setString(1,originDirectStr);
                    statement.setString(2,destinationDirectStr);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        routeMapSet.add(mapRouteMap(resultSet));
                    }
                } finally {
                    closeQuietly(resultSet);
                }
            }
        }

        for (String destinationReturnStr: destinationReturn) {
            for (String originReturnStr: originReturn ) {
                ResultSet resultSet = null;
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ROUTEMAP_BY_ORIGIN_AND_DESTINATION)) {
                    statement.setString(1,destinationReturnStr);
                    statement.setString(2,originReturnStr);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        routeMapSet.add(mapRouteMap(resultSet));
                    }
                } finally {
                    closeQuietly(resultSet);
                }
            }
        }
        return routeMapSet;
    }

    private RouteMap mapRouteMap(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);

        Long airlineId = resultSet.getLong(2);
        String airlineName = resultSet.getString(3);

        Long airportOriginId = resultSet.getLong(4);
        String airportOriginCode = resultSet.getString(5);
        String airportOriginCity = resultSet.getString(7);
        String airportOriginCountry = resultSet.getString(6);

        Long airportDestinationId = resultSet.getLong(8);
        String airportDestinationCode = resultSet.getString(9);
        String airportDestinationCity = resultSet.getString(11);
        String airportDestinationCountry = resultSet.getString(10);

        Long directionId = resultSet.getLong(12);
        String directionName = resultSet.getString(13);

        Airline airline = new Airline(airlineId,airlineName);
        Airport origin = new Airport(airportOriginId,airportOriginCode,airportOriginCountry,airportOriginCity);
        Airport destination = new Airport(airportDestinationId,airportDestinationCode,airportDestinationCountry,airportDestinationCity);
        Direction direction = new Direction(directionId,directionName);

        return new RouteMap(id,airline,origin,destination,direction);
    }

}
