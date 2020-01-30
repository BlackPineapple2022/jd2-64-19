package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.service.global.ScheduleService;
import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final FlightDAO flightDAO = FlightDAOImpl.getInstance();

    private static final ScheduleService INSTANCE = new ScheduleServiceImpl();

    private ScheduleServiceImpl() {
    }

    public static ScheduleService getInstance() {
        return INSTANCE;
    }

    @Override
    @Deprecated
    public Schedule getSchedule(AirlineEnum airlineEnum, Airport origin, Airport destination, LocalDate startingDate, Integer days) {
        return new Schedule();
    }

    @Override
    public Schedule getSchedule(RouteMap routeMap) {
        log.info("Getting schedule from base for routeMap: "+ routeMap);
        try {
            Schedule schedule = flightDAO.getSchedule(routeMap);
            log.info("Getting schedule from Base successfully ended, found dates: "+schedule.getScheduleSet().size());
            return schedule;
        }catch (SQLException e){
            log.error("Error while getting schedule from Base for routeMap: "+routeMap,e);
        }
        return null;
    }
}
