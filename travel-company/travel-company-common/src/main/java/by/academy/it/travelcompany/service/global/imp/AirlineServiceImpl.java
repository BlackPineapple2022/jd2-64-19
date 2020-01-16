package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.dao.impl.AirlineDAOImpl;
import by.academy.it.travelcompany.service.global.AirlineService;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AirlineServiceImpl implements AirlineService {

    AirlineDAO airlineDAO = AirlineDAOImpl.getInstance();

    private static final AirlineServiceImpl INSTANCE = new AirlineServiceImpl();

    private AirlineServiceImpl() {
    }

    public static AirlineServiceImpl getInstance() {
        return INSTANCE;
    }

//CRUD

    @Override
    public Airline add(Airline airline) {
        log.info("add new airline to Base{}", airline);
        try {
            Long id = airlineDAO.create(airline);
            airline.setId(id);
            log.info("result {}", id);
            return airline;
        } catch (SQLException e) {
            log.error("Error while creating airline " + airline, e);
        }
        return null;
    }

    @Override
    public Optional<Airline> getById(Long id) {
        log.info("getting airline from Base{}", id);
        try {
            Optional<Airline> airline = airlineDAO.read(id);
            log.info("result {}", airline);
            return airline;
        } catch (SQLException e) {
            log.error("Error while getting airline ", e);
        }
        return Optional.empty();
    }

    @Override
    public Airline update(Airline airline) {
        log.info("updating airline from Base{}", airline);
        try {
            int update = airlineDAO.update(airline);
            log.info("result {}", update);
        } catch (SQLException e) {
            log.error("Error while updating airline " + airline, e);
        }
        return airline;
    }

    @Override
    public void delete(Long id) {
        log.info("deleting airline from Base{}", id);
        try {
            airlineDAO.delete(id);
            log.info("result {}", id);
        } catch (SQLException e) {
            log.error("Error while deleting airline " + id, e);
        }
    }

//!CRUD

    @Override
    public List<Airline> getAll() {
        log.info("Getting all airline from Base{}");
        try {
            return airlineDAO.getAll();

        } catch (SQLException e) {
            log.error("Error while getting all airline", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void installAllAirline() {
        log.info("Installing all airline to Base{}");
        try {
            for (AirlineEnum aE : AirlineEnum.values()) {
                Airline a = new Airline(null, aE.toString());
                airlineDAO.create(a);
            }
            log.info("Installing all airline to Base{} successfully ended");
        }catch (SQLException e){
            log.error("Error while installing all airline to base", e);
        }
    }

    @Override
    public Long getIdFromBase(Airline airline) {
        log.info("Getting airline id from Base{}", airline);
        Long result = null;
        try {
            result = airlineDAO.getIdByName(airline.getAirlineName());
            log.info("result {}", result);
            return result;
        } catch (SQLException e) {
            log.error("Error while getting airline id" + airline, e);
        }
        return result;
    }
}
