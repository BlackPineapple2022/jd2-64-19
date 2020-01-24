package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.dao.FlightScannerJournalDAO;
import by.academy.it.travelcompany.dao.impl.AirlineDAOImpl;
import by.academy.it.travelcompany.dao.impl.FlightScannerJournalDAOImpl;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class FlightScannerJournalServiceImpl {

    FlightScannerJournalDAO flightScannerJournalDAO = FlightScannerJournalDAOImpl.getInstance();

    private static final FlightScannerJournalServiceImpl INSTANCE = new FlightScannerJournalServiceImpl();

    private FlightScannerJournalServiceImpl() {
    }

    public static FlightScannerJournalServiceImpl getInstance() {
        return INSTANCE;
    }

    public Long createJournalEntry(Long routeMapId) {
        log.info("Add new entry to Base{}", routeMapId);
        try {
            Long id = flightScannerJournalDAO.createJournalEntry(routeMapId);
            log.info("Result {}", id);
            return id;
        } catch (SQLException e) {
            log.error("Error while creating entry " + routeMapId, e);
        }
        return null;
    }

    public void deleteDateOnJournalEntry(Long routeMapId) {
        log.info("Deleting scanned date on entry", routeMapId);
        try {
            flightScannerJournalDAO.deleteCheckedDate(routeMapId);
        } catch (SQLException e) {
            log.error("Error while deleting date on entry " + routeMapId, e);
        }
    }

    public void updateDateOnJournalEntry(Long routeMapId) {
        log.info("Updating date on entry", routeMapId);
        try {
            flightScannerJournalDAO.updateJournalEntry(routeMapId);
        } catch (SQLException e) {
            log.error("Error while updating date on entry " + routeMapId, e);
        }
    }

    public Long getFirstRouteMapIdWithNullDateTime(String airlineName) {
        log.info("Getting entry with date time = null ", airlineName);
        Long result = null;
        try {
           return flightScannerJournalDAO.getFirstNullDateTime(airlineName);
        } catch (SQLException e) {
            log.error("Error while getting entry with null date time " + airlineName, e);
        }
        return result;
    }

    public Long getOlderRouteMapId(String airlineName) {
        log.info("Getting entry with older date time ", airlineName);
        Long result = null;
        try {
            return flightScannerJournalDAO.getOlder(airlineName);
        } catch (SQLException e) {
            log.error("Error while getting entry with older date time" + airlineName, e);
        }
        return result;
    }

}
