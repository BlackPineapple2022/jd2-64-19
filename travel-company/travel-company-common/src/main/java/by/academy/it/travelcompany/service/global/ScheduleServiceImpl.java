package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;

import java.time.LocalDate;

public class ScheduleServiceImpl implements ScheduleService {

    private static final ScheduleService INSTANCE = new ScheduleServiceImpl();

    private ScheduleServiceImpl() {

    }

    public static ScheduleService getInstance() {
        return INSTANCE;
    }

    @Override
    public Schedule getSchedule(Airline airline, Airport origin, Airport destination, LocalDate startingDate, Integer days) {

        Schedule schedule = new Schedule(airline, origin, destination, startingDate, days);
        //TODO: make real schedule service

        return schedule;
    }
}
