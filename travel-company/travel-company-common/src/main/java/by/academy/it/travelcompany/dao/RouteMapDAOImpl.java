package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteMapDAOImpl extends AbstractDAO implements RouteMapDAO {

    private static final String REGEX_ROUTE_MAP="--";

    private static final RouteMapDAO INSTANCE = new RouteMapDAOImpl();

    private RouteMapDAOImpl() {
        super(LoggerFactory.getLogger(UserDAOImpl.class));
    }

    public static RouteMapDAO getInstance() {
        return INSTANCE;
    }

    public static final String SELECT_ALL_ROUTEMAP = "SELECT * FROM routemap";
    public static final String INSERT_ROUTEMAP = "INSERT INTO routemap (airline_id, origin_airport_id, destination_airport_id,direction_id ) VALUE (?,?,?,?)";
    public static final String SELECT_AIRLINE = "SELECT * FROM airline WHERE airline_name = ?";
    public static final String SELECT_AIRPORT = "SELECT * FROM airport WHERE airport_code = ?";
    public static final String SELECT_DIRECTION = "SELECT * FROM direction WHERE direction_name = ?";


    @Override
    public Long create(String s) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ROUTEMAP, Statement.RETURN_GENERATED_KEYS)) {

            int airlineID;
            int originAirportID;
            int destinationAirportID;
            int directionID;

            String[] sArr = s.split(REGEX_ROUTE_MAP);

            String airlineString = sArr[0];
            String originAirportString = sArr[1];
            String destinationAirportString = sArr[2];
            String directionString = sArr[3];

            statement.setInt(1, getAirlineIdFromString(airlineString));
            statement.setInt(2, getAirportIdFromString(originAirportString));
            statement.setInt(3, getAirportIdFromString(destinationAirportString));
            statement.setInt(4, getDirectionIdFromString(directionString));

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
    public Optional<String> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(String s) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<String> getAll() throws SQLException {
        return null;
    }

    private int getAirlineIdFromString(String str) throws SQLException{
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRLINE)) {
            statement.setString(1,str);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private int getAirportIdFromString(String str) throws SQLException{
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRPORT)) {
            statement.setString(1,str);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private int getDirectionIdFromString(String str) throws SQLException{
        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DIRECTION)) {
            statement.setString(1,str);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }
}
