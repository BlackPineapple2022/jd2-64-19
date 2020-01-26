package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.dao.impl.AirportDAOImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

//CRUD

    public Airport create(Airport airport) {
        log.info("Add new airport to Base{}", airport);
        try {
            Long id = airportDAO.create(airport);
            airport.setId(id);
            log.info("Result {}", id);
            return airport;
        } catch (SQLException e) {
            log.error("Error while creating airport " + airport, e);
        }
        return null;
    }

    public Optional<Airport> read(Long id) {
        log.info("Getting airport from Base{}", id);
        try {
            Optional<Airport> airport = airportDAO.read(id);
            log.info("Result {}", airport);
            return airport;
        } catch (SQLException e) {
            log.error("Error while getting airport ", e);
        }
        return Optional.empty();
    }

    public Airport update(Airport airport) {
        log.info("Updating airport {}", airport);
        try {
            int update = airportDAO.update(airport);
            log.info("Result {}", update);
        } catch (SQLException e) {
            log.error("Error while updating airport " + airport, e);
        }
        return airport;
    }

    public void delete(Long id) {
        log.info("Deleting airport id = {}", id);
        try {
            int delete = airportDAO.delete(id);
            log.debug("Result {}", delete);
        } catch (SQLException e) {
            log.error("Error while deleting airport id=" + id, e);
        }
    }

//!CRUD

    public List<Airport> getAll() {
        log.info("Getting all airport from Base{}");
        try {
            return airportDAO.getAll();
        } catch (SQLException e) {
            log.error("Error while getting all airport", e);
        }
        return Collections.emptyList();
    }

    public Airport getAirportByCode(String code){
        log.info("Get airport by code");
        try{
            return airportDAO.getByCode(code);
        } catch (SQLException e){
            log.error("Error while getting airport by code: "+code,e);
        }
        return null;
    }

    public void installAllAirport(){
        log.info("Installing all airport to Base{}");
        try {
            Set<Airport> allAirport = AirportInfoCentre.getAllAirports();
            for (Airport a : allAirport) {
                airportDAO.create(a);
            }
        }catch (SQLException e){
            log.error("Error when installing all airport to Base{}");
        }
        log.info("Installing all airport to Base{} successfully ended");
    }

}
