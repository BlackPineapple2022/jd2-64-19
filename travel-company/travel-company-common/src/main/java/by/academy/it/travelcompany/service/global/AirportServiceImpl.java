package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.dao.AirportDAOImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AirportServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportInfoCentre.class);
    private final AirportDAO airportDAO = AirportDAOImpl.getInstance();

    private static final AirportServiceImpl INSTANCE = new AirportServiceImpl();

    private AirportServiceImpl() {

    }

    public static AirportServiceImpl getInstance() {
        return INSTANCE;
    }

    public Airport addToBase(Airport airport) {
        LOGGER.info("add new airport to Base{}", airport);
        try {
            Long id = airportDAO.create(airport);
            LOGGER.info("result {}", id);
        } catch (SQLException e) {
            LOGGER.error("Error while creating airport " + airport, e);
        }
        return airport;
    }

    public List<Airport> getAllAirport() {
        LOGGER.info("Get all airport");
        try {
            return airportDAO.getAll();
        } catch (SQLException e) {
            LOGGER.error("Error while getting all airport", e);
        }
        return Collections.emptyList();
    }

    public void delete(Long id) {
        LOGGER.info("deleting airport id = {}", id);
        try {
            int delete = airportDAO.delete(id);
            LOGGER.debug("result {}", delete);
        } catch (SQLException e) {
            LOGGER.error("Error while deleting airport id=" + id, e);
        }
    }

    public Airport update(Airport airport) {
        LOGGER.info("updating airport {}", airport);
        try {
            int update = airportDAO.update(airport);
            LOGGER.info("result {}", update);
        } catch (SQLException e) {
            LOGGER.error("Error while updating airport " + airport, e);
        }
        return airport;
    }


}
