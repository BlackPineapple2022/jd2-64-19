package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.RoundTripDAO;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RoundTripDAOImpl extends AbstractDAO implements RoundTripDAO {

    private static final RoundTripDAO INSTANCE = new RoundTripDAOImpl();

    private RoundTripDAOImpl() {
        super(LoggerFactory.getLogger(RoundTripDAOImpl.class));
    }

    public static RoundTripDAO getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_ROUNDTRIP = "INSERT INTO roundtrip (direct_flight_id, return_flight_id, price) VALUE (?,?,?)";
    private static final String DELETE_ROUNDTRIP = "DELETE FROM roundtrip WHERE id=?";

    private static final String SELECT_ALL_TRIP_BY_SEARCH_ID = "SELECT r.id,r.direct_flight_id,r.return_flight_id,r.price FROM roundtrip r JOIN flight fd ON r.direct_flight_id = fd.id WHERE fd.search_id= ?";

    private static final String SELECT_ALL_TRIP_NOT_IN_FAVOURITE_LIST_BY_SEARCH_ID = "SELECT r.id FROM roundtrip r JOIN flight f ON r.direct_flight_id = f.id " +
            "LEFT JOIN favourite_list f_l ON r.id=f_l.trip_id WHERE f_l.id IS NULL AND f.search_id = ?";


//CRUD

    @Override
    public Long create(Trip trip) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ROUNDTRIP, Statement.RETURN_GENERATED_KEYS)) {

            Long directFlightId = trip.getFlights().get(0).getId();
            Long returnFlightId = trip.getFlights().get(1).getId();
            Double price = trip.getPrice();

            statement.setLong(1, directFlightId);
            statement.setLong(2, returnFlightId);
            statement.setDouble(3, price);

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
    public Optional<Trip> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(Trip trip) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ROUNDTRIP)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

//!CRUD

    @Override
    public List<Trip> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Trip> getAllBySearchId(Long searchId) throws SQLException {
        ResultSet resultSet = null;
        List<Trip> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TRIP_BY_SEARCH_ID)) {
            statement.setLong(1, searchId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                Flight flightDirect = new Flight(resultSet.getLong(2), null, null, null, null, null, null);
                Flight flightReturn = new Flight(resultSet.getLong(3), null, null, null, null, null, null);
                List<Flight> flights = new ArrayList<>();
                flights.add(flightDirect);
                flights.add(flightReturn);
                Double price = resultSet.getDouble(4);
                Trip t = new Trip(flights, price, searchId);
                t.setId(id);
                t.setSearchId(searchId);
                result.add(t);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public void deleteAllBySearchIdButFavourite(List<Long> searchIdList) throws SQLException {
        List<Long> ids = new ArrayList<>();
        ResultSet resultSet = null;

        for (Long l : searchIdList) {
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TRIP_NOT_IN_FAVOURITE_LIST_BY_SEARCH_ID)) {
                statement.setLong(1, l);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ids.add(resultSet.getLong(1));
                }
            } finally {
                closeQuietly(resultSet);
            }
        }

        for (Long l : ids) {
            delete(l);
        }

    }

}
