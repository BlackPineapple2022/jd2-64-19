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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RouteMapDAOImpl extends AbstractDAO implements RouteMapDAO {

    private static final RouteMapDAO INSTANCE = new RouteMapDAOImpl();

    private RouteMapDAOImpl() {
        super(LoggerFactory.getLogger(RouteMapDAOImpl.class));
    }

    public static RouteMapDAO getInstance() {
        return INSTANCE;
    }

    public static final String INSERT_ROUTEMAP = "INSERT INTO routemap (airline_id, origin_airport_id, destination_airport_id,direction_id ) VALUE (?,?,?,?)";
    public static final String SELECT_ROUTEMAP = "SELECT r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name" +
            " FROM routemap r JOIN airline al ON r.airline_id = al.id JOIN airport apo ON r.origin_airport_id = apo.id JOIN airport apd ON r.destination_airport_id = apd.id JOIN direction d on r.direction_id=d.id WHERE id=?";
    public static final String UPDATE_ROUTEMAP = "UPDATE routemap SET airline_id = ?, origin_airport_id = ?, destination_airport_id = ?, direction_id = ? WHERE id = ?";
    public static final String DELETE_ROUTEMAP = "DELETE FROM routemap WHERE id = ?";

    public static final String SELECT_ALL_ROUTEMAP = "SELECT * FROM routemap";

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

    private RouteMap mapRouteMap(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(1);

        Long airlineId = resultSet.getLong(2);
        String airlineName = resultSet.getString(3);

        Long airportOriginId = resultSet.getLong(4);
        String airportOriginCode = resultSet.getString(5);
        String airportOriginCity = resultSet.getString(6);
        String airportOriginCountry = resultSet.getString(7);

        Long airportDestinationId = resultSet.getLong(8);
        String airportDestinationCode = resultSet.getString(9);
        String airportDestinationCity = resultSet.getString(10);
        String airportDestinationCountry = resultSet.getString(11);

        Long directionId = resultSet.getLong(12);
        String directionName = resultSet.getString(13);

        Airline airline = new Airline(airlineId,airlineName);
        Airport origin = new Airport(airportOriginId,airportOriginCode,airportOriginCountry,airportOriginCity);
        Airport destination = new Airport(airportDestinationId,airportDestinationCode,airportDestinationCountry,airportDestinationCity);
        Direction direction = new Direction(directionId,directionName);

        return new RouteMap(id,airline,origin,destination,direction);

    }


}
