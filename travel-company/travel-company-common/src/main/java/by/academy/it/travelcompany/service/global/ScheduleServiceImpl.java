package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.time.LocalDate;

public class ScheduleServiceImpl implements ScheduleService{

    private static final ScheduleService INSTANCE = new ScheduleServiceImpl();

    private ScheduleServiceImpl(){

    }

    public static ScheduleService getInstance(){
        return INSTANCE;
    }

    @Override
    public LocalDate getNextLocalDate (LocalDate localDate, Airline airline, Airport origin, Airport destination){
        return localDate.plusDays(1);
    }

}
