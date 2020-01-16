package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.dao.impl.RouteMapDAOImpl;

import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class RouteMapServiceImpl {

    private final RouteMapDAO routeMapDAO = RouteMapDAOImpl.getInstance();
    private static final RouteMapServiceImpl INSTANCE = new RouteMapServiceImpl();

    private RouteMapServiceImpl() {
    }

    public static RouteMapServiceImpl getInstance() {
        return INSTANCE;
    }

    public void addRouteMapToBase(RouteMap routeMap) {
        log.info("add new routeMap to Base{}", routeMap);
        try {
            Long id = routeMapDAO.create(routeMap);
            log.info("result {}", id);
        } catch (SQLException e) {
            log.error("Error while creating airport " + routeMap, e);
        }
    }


}
