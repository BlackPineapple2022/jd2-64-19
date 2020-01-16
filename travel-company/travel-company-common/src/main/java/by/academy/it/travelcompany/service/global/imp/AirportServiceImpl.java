package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.dao.impl.AirportDAOImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
public class AirportServiceImpl {

    private final AirportDAO airportDAO = AirportDAOImpl.getInstance();

    private static final AirportServiceImpl INSTANCE = new AirportServiceImpl();

    private AirportServiceImpl() {
    }

    public static AirportServiceImpl getInstance() {
        return INSTANCE;
    }

    public Long add(Airport airport) {
        log.info("add new airport to Base{}", airport);
        try {
            Long id = airportDAO.create(airport);
            airport.setId(id);
            log.info("result {}", id);
            return id;
        } catch (SQLException e) {
            log.error("Error while creating airport " + airport, e);
        }
        return null;
    }

    //TODO: read

    public Airport update(Airport airport) {
        log.info("updating airport {}", airport);
        try {
            int update = airportDAO.update(airport);
            log.info("result {}", update);
        } catch (SQLException e) {
            log.error("Error while updating airport " + airport, e);
        }
        return airport;
    }

    public void delete(Long id) {
        log.info("deleting airport id = {}", id);
        try {
            int delete = airportDAO.delete(id);
            log.debug("result {}", delete);
        } catch (SQLException e) {
            log.error("Error while deleting airport id=" + id, e);
        }
    }

    public List<Airport> getAllAirport() {
        log.info("Get all airport");
        try {
            return airportDAO.getAll();
        } catch (SQLException e) {
            log.error("Error while getting all airport", e);
        }
        return Collections.emptyList();
    }

    public void installAllAirportToBase(){
        Set<Airport> allAirport = AirportInfoCentre.getAllAirports();
        for (Airport a:allAirport ) {
            add(a);
        }
    }





}
