package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.time.LocalDate;

public interface ScheduleService {
    public LocalDate getNextLocalDate (LocalDate localDate, Airline airline, Airport origin, Airport destination);

}
