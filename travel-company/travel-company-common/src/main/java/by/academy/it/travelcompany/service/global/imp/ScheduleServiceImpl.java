package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.service.global.ScheduleService;
import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;

import java.sql.SQLException;
import java.time.LocalDate;

public class ScheduleServiceImpl implements ScheduleService {

    private static final FlightDAO FLIGHT_DAO = FlightDAOImpl.getInstance();

    private static final ScheduleService INSTANCE = new ScheduleServiceImpl();

    private ScheduleServiceImpl() {

    }

    public static ScheduleService getInstance() {
        return INSTANCE;
    }


    @Override
    public Schedule getSchedule(AirlineEnum airlineEnum, Airport origin, Airport destination, LocalDate startingDate, Integer days) {
        return new Schedule();
    }

    @Override
    public Schedule getSchedule(RouteMap routeMap) {
        try {
            return FLIGHT_DAO.getSchedule(routeMap);
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
