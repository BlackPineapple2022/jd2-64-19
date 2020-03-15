package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelcompany.travelitem.schedule.Schedule;

import java.time.LocalDate;

public interface ScheduleService {

    @Deprecated
    Schedule getSchedule(AirlineEnum airlineEnum, Airport origin, Airport destination, LocalDate startingDate, Integer days);

    Schedule getSchedule(RouteMap routeMap);

}
