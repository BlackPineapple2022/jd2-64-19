package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.dao.AirportDAOImpl;
import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.dao.RouteMapDAOImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RouteMapServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteMapServiceImpl.class);
    private final RouteMapDAO routeMapDAO = RouteMapDAOImpl.getInstance();
    private static final RouteMapServiceImpl INSTANCE = new RouteMapServiceImpl();


    private RouteMapServiceImpl() {
    }

    public static RouteMapServiceImpl getInstance() {
        return INSTANCE;
    }

    public void addToBase(String routeMap) {
        LOGGER.info("add new routeMap to Base{}", routeMap);
        try {
            Long id = routeMapDAO.create(routeMap);
            LOGGER.info("result {}", id);
        } catch (SQLException e) {
            LOGGER.error("Error while creating airport " + routeMap, e);
        }
    }


}
