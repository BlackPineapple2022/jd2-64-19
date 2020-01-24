package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.dao.FlightScannerJournalDAO;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class FlightScannerJournalDAOImpl extends AbstractDAO implements FlightScannerJournalDAO {

    private static final FlightScannerJournalDAO INSTANCE = new FlightScannerJournalDAOImpl();

    private FlightScannerJournalDAOImpl() {
        super(LoggerFactory.getLogger(AirlineDAOImpl.class));
    }

    public static FlightScannerJournalDAO getInstance() {
        return INSTANCE;
    }

    private final static String INSERT_ENTRY = "INSERT INTO flight_scanner_journal (routemap_id, is_active) VALUE (?,?)";
    private final static String DELETE_DATE_ON_ENTRY = "UPDATE flight_scanner_journal SET scanned_time = null WHERE routemap_id = ?";
    private final static String UPDATE_DATE_ON_ENTRY = "UPDATE flight_scanner_journal SET scanned_time = now() WHERE routemap_id = ? ";
    private final static String SELECT_FIRST_NULL_BY_AIRLINE = "SELECT f_s_j.routemap_id FROM flight_scanner_journal f_s_j " +
            "JOIN routemap r ON f_s_j.routemap_id = r.id " +
            "JOIN airline a ON r.airline_id=a.id " +
            "WHERE a.airline_name = ? AND f_s_j.scanned_time is null LIMIT 1";
    private final static String SELECT_OLDER_BY_AIRLINE = "SELECT f_s_j.routemap_id FROM flight_scanner_journal f_s_j " +
            "JOIN routemap r ON f_s_j.routemap_id = r.id " +
            "JOIN airline a ON r.airline_id=a.id " +
            "WHERE a.airline_name = ? ORDER BY f_s_j.scanned_time ASC LIMIT 1";

    @Override
    public Long createJournalEntry(Long routeMapId) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ENTRY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, routeMapId);
            statement.setBoolean(2, true);
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
    public void deleteCheckedDate(Long routeMapId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DATE_ON_ENTRY)) {
            statement.setLong(1, routeMapId);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateJournalEntry(Long routeMapId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DATE_ON_ENTRY)) {
            statement.setLong(1, routeMapId);
            statement.executeUpdate();
        }
    }

    @Override
    public Long getFirstNullDateTime(String airlineName) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(SELECT_FIRST_NULL_BY_AIRLINE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, airlineName);
            statement.executeQuery();
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

    @Override
    public Long getOlder(String airlineName) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(SELECT_OLDER_BY_AIRLINE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, airlineName);
            statement.executeQuery();
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
