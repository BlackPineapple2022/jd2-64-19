package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.airport.Airport;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportDAOImpl extends AbstractDAO implements AirportDAO {

    private static final AirportDAO INSTANCE = new AirportDAOImpl();

    private AirportDAOImpl() {
        super(LoggerFactory.getLogger(UserDAOImpl.class));
    }

    public static AirportDAO getInstance() {
        return INSTANCE;
    }

    public static final String SELECT_ALL_AIRPORT = "SELECT * FROM airport";
    public static final String INSERT_AIRPORT = "INSERT INTO airport (airport_code, country, city) VALUE (?,?,?)";
    public static final String DELETE_AIRPORT_BY_ID = "DELETE FROM airport WHERE id = ?";

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
        return Optional.empty();
    }

    @Override
    public int update(Airport airport) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_AIRPORT_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

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

    private Airport mapAirport(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String airportCode = resultSet.getString("airport_code");
        String city = resultSet.getString("city");
        String country = resultSet.getString("country");
        return new Airport(id,airportCode,city,country);
    }

}
