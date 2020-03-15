package by.academy.it.travelcompany.travelcompany.dao.impl;

import by.academy.it.travelcompany.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.travelcompany.travelitem.airline.Airline;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AirlineDAOImpl extends AbstractDAO implements AirlineDAO {

    private static final AirlineDAO INSTANCE = new AirlineDAOImpl();

    private AirlineDAOImpl() {
        super(LoggerFactory.getLogger(AirlineDAOImpl.class));
    }

    public static AirlineDAO getInstance() {
        return INSTANCE;
    }

    private final String INSERT_AIRLINE = "INSERT INTO airline (airline_name) VALUES (?)";
    private final String SELECT_AIRLINE = "SELECT * FROM airline WHERE id = ?";
    private final String UPDATE_AIRLINE = "UPDATE airline SET airline_name = ? WHERE id = ?";
    private final String DELETE_AIRLINE = "DELETE FROM airline WHERE id = ?";

    private final String SELECT_ALL_AIRLINE = "SELECT * FROM airline ORDER BY id ASC";
    private final String SELECT_AIRLINE_BY_NAME = "SELECT * FROM airline WHERE airline_name = ?";

//CRUD

    @Override
    public Long create(Airline airline) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_AIRLINE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, airline.getAirlineName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
                return result;
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional<Airline> read(Long id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRLINE)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Airline result = new Airline(id, resultSet.getString(2));
                return Optional.of(result);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public int update(Airline airline) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_AIRLINE)) {
            statement.setString(1, airline.getAirlineName());
            statement.setLong(2, airline.getId());
            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_AIRLINE)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

//!CRUD

    @Override
    public List<Airline> getAll() throws SQLException {
        List<Airline> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AIRLINE)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Airline(resultSet.getLong(1), resultSet.getString(2)));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Long getIdByName(String name) throws SQLException {
        Long result = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRLINE_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
                return result;
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }
}
