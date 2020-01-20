package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.dao.impl.RouteMapDAOImpl;
import by.academy.it.travelcompany.service.global.FlightService;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO = FlightDAOImpl.getInstance();

    private static final FlightService INSTANCE = new FlightServiceImpl();

    private FlightServiceImpl() {
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

    @Override
    public Flight create(Flight flight) {
        log.info("Add new flight to Base{}", flight);
        try {
            Long id = flightDAO.create(flight);
            flight.setId(id);
            log.info("Result {}", id);
            return flight;
        } catch (SQLException e) {
            log.error("Error while creating flight " + flight, e);
        }
        return null;
    }

    @Override
    public Optional<Flight> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Flight update(Flight flight) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<Flight> getAll() {
        return null;
    }

    public List<Flight> getAllFlightBySearchId(Long searchId) {
        log.info("Getting all flights with search id: " + searchId);
        List<Flight> result = new ArrayList<>();
        try {
            return flightDAO.getAllFlightBySearchId(searchId);
        } catch (SQLException e) {
            log.error("Error while getting all flight with search id: ", e);
        }
        return result;
    }
}

