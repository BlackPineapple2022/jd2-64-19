package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.RouteMapDAO;
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
    private final FlightScannerJournalServiceImpl flightScannerJournalService = FlightScannerJournalServiceImpl.getInstance();


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
            FlightScannerJournalServiceImpl.getInstance().createJournalEntry(id);
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
            return routeMapDAO.getByParam(airlineStr,originAirportCode,destinationAirportCode,directionStr);
        } catch (SQLException e) {
            log.error("Error while getting routeMap", e);
        }
        return Optional.empty();
    }

public Set<RouteMap> getRouteMapSetByAirportCodeSets(Set<String> originsDirect,Set<String> destinationsDirect,Set<String> destinationsReturn,Set<String> originsReturn){
        log.info("Getting routeMap set from Base{}");
        try{
            Set<RouteMap> routeMaps = routeMapDAO.getRouteMapSetByAirportCodeSets(originsDirect,destinationsDirect,destinationsReturn,originsReturn);
            log.info("Getting routeMap successfully completed, result: "+routeMaps );
            return routeMaps;
        }catch (SQLException e){
            log.error("Error while getting routeMap set from Base{}");
        }
        return Collections.emptySet();
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
                Long id = routeMapDAO.create(routeMap);
                flightScannerJournalService.createJournalEntry(id);

            }
            log.info("Installing all routeMap to Base{} successfully ended");
        } catch (SQLException e) {
            log.error("Error while installing all routeMap to Base{}", e);
        }

    }


}
