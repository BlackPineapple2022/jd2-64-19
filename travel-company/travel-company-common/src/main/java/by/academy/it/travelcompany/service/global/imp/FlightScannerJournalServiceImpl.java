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



}
