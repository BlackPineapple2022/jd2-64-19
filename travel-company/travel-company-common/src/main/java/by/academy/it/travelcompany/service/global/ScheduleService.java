package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;

import java.time.LocalDate;

public interface ScheduleService {
    public Schedule getSchedule(Airline airline, Airport origin, Airport destination, LocalDate startingDate, Integer days);

}
