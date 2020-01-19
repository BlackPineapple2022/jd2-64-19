package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.AirlineDAO;
import by.academy.it.travelcompany.dao.AirportDAO;
import by.academy.it.travelcompany.dao.DirectionDAO;
import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.dao.impl.AirlineDAOImpl;
import by.academy.it.travelcompany.dao.impl.AirportDAOImpl;
import by.academy.it.travelcompany.dao.impl.DirectionDAOImpl;
import by.academy.it.travelcompany.dao.impl.RouteMapDAOImpl;

import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.travelitem.direction.Direction;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.*;

@Slf4j
public class RouteMapServiceImpl {

    private final RouteMapDAO routeMapDAO = RouteMapDAOImpl.getInstance();
    private final AirlineDAO airlineDAO = AirlineDAOImpl.getInstance();
    private final AirportDAO airportDAO = AirportDAOImpl.getInstance();
    private final DirectionDAO directionDAO = DirectionDAOImpl.getInstance();


    private static final RouteMapServiceImpl INSTANCE = new RouteMapServiceImpl();

    private RouteMapServiceImpl() {
    }

    public static RouteMapServiceImpl getInstance() {
        return INSTANCE;
    }

//CRUD

    public RouteMap create(RouteMap routeMap) {
        log.info("Add new routeMap to Base{}", routeMap);
        try {
            Long id = routeMapDAO.create(routeMap);
            log.info("Result {}", id);
        } catch (SQLException e) {
            log.error("Error while creating routeMap " + routeMap, e);
        }
        return routeMap;
    }

    public Optional<RouteMap> read(Long id) {
        log.info("Getting routeMap from Base{}", id);
        try {
            Optional<RouteMap> routeMap = routeMapDAO.read(id);
            log.info("Result {}", routeMap);
            return routeMap;
        } catch (SQLException e) {
            log.error("Error while getting routeMap ", e);
        }
        return Optional.empty();
    }

    public RouteMap update(RouteMap routeMap) {
        log.info("Updating routeMap {}", routeMap);
        try {
            int update = routeMapDAO.update(routeMap);
            log.info("Result {}", update);
        } catch (SQLException e) {
            log.error("Error while updating routeMap " + routeMap, e);
        }
        return routeMap;
    }

    public void delete(Long id) {
        log.info("Deleting routeMap id = {}", id);
        try {
            int delete = routeMapDAO.delete(id);
            log.info("Result {}", delete);
        } catch (SQLException e) {
            log.error("Error while deleting routeMap id=" + id, e);
        }
    }

//!CRUD

    public List<RouteMap> getAll() {
        log.info("Getting all routeMap from Base{}");
        try {
            return routeMapDAO.getAll();
        } catch (SQLException e) {
            log.error("Error while getting all routeMap", e);
        }
        return Collections.emptyList();
    }

    public Optional<RouteMap> getRouteMapByParam(String airlineStr, String originAirportCode, String destinationAirportCode, String directionStr) {
        log.info("Getting routeMap from Base{} by params");
        try {
            return routeMapDAO.getRouteMapByParam(airlineStr,originAirportCode,destinationAirportCode,directionStr);
        } catch (SQLException e) {
            log.error("Error while getting routeMap", e);
        }
        return Optional.empty();
    }


    public void installAllRouteMap() {
        log.info("Installing all routeMap to Base{}");
        try {
            List<String> routeMapStringSet = AirportInfoCentre.getRouteMapStringList(
                    new ArrayList<>(AirportInfoCentre.getAllStartAirports()),
                    new ArrayList<>(AirportInfoCentre.getAllAirportsFromStart())
            );
            for (String s : routeMapStringSet) {
                String airlineName = s.split("--")[0];
                String originAirportCode = s.split("--")[1];
                String destinationAirportCode = s.split("--")[2];
                String directionName = s.split("--")[3];

                Airline airline = new Airline(AirlineServiceImpl.getInstance().getIdByName(airlineName), airlineName);
                Airport originAirport = AirportServiceImpl.getInstance().getAirportByCode(originAirportCode);
                Airport destinationAirport = AirportServiceImpl.getInstance().getAirportByCode(destinationAirportCode);
                Direction direction = new Direction(DirectionServiceImpl.getInstance().getIdByName(directionName), directionName);

                RouteMap routeMap = new RouteMap(null, airline, originAirport, destinationAirport, direction);
                routeMapDAO.create(routeMap);
            }
            log.info("Installing all routeMap to Base{} successfully ended");
        } catch (SQLException e) {
            log.error("Error while installing all routeMap to Base{}", e);
        }

    }


}
