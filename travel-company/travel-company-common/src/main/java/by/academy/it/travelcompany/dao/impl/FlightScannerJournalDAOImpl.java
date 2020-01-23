package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.dao.FlightScannerJournalDAO;
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

    @Override
    public Long createJournalEntry(Long routeMapId) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ENTRY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, routeMapId);
            statement.setBoolean(2,true);
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
}
