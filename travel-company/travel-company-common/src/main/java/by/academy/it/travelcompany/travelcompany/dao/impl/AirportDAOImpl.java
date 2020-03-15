package by.academy.it.travelcompany.travelcompany.dao.impl;

import by.academy.it.travelcompany.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.travelcompany.travelitem.airport.Airport;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AirportDAOImpl extends AbstractDAO implements AirportDAO {

    private static final AirportDAO INSTANCE = new AirportDAOImpl();

    private AirportDAOImpl() {
        super(LoggerFactory.getLogger(AirportDAOImpl.class));
    }

    public static AirportDAO getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_AIRPORT = "INSERT INTO airport (airport_code, country, city) VALUE (?,?,?)";
    private static final String SELECT_AIRPORT = "SELECT * FROM airport WHERE id = ?";
    private static final String UPDATE_AIRPORT = "UPDATE airport SET airport_code = ? , country = ?, city = ? WHERE id =?";
    private static final String DELETE_AIRPORT = "DELETE FROM airport WHERE id = ?";

    private static final String SELECT_ALL_AIRPORT = "SELECT * FROM airport";
    private static final String SELECT_AIRPORT_BY_CODE = "SELECT * FROM airport WHERE airport_code = ?";

//CRUD

    @Override
    public Long create(Airport airport) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_AIRPORT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, airport.getCode());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());

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
    public Optional<Airport> read(Long id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRPORT)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Airport result = mapAirport(resultSet);
                return Optional.of(result);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public int update(Airport airport) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_AIRPORT)) {

            statement.setString(1, airport.getCode());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            statement.setLong(4, airport.getId());

            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_AIRPORT)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

//!CRUD

    @Override
    public List<Airport> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<Airport> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AIRPORT)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(mapAirport(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Airport getByCode(String code) throws SQLException {
        ResultSet resultSet = null;
        Airport result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRPORT_BY_CODE)) {
            statement.setString(1, code);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = mapAirport(resultSet);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private Airport mapAirport(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String airportCode = resultSet.getString("airport_code");
        String city = resultSet.getString("city");
        String country = resultSet.getString("country");
        return new Airport(id, airportCode, country, city);
    }

}
