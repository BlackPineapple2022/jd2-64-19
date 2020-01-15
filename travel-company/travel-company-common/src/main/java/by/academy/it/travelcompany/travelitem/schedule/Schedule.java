package by.academy.it.travelcompany.travelitem.schedule;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private Airline airline;
    private Airport origin;
    private Airport destination;
    private LocalDate startingDate;
    private Integer days;
    private Set<LocalDate> scheduleSet = new TreeSet<>();

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Set<LocalDate> getScheduleSet() {
        return scheduleSet;
    }

    public void setScheduleSet(Set<LocalDate> scheduleSet) {
        this.scheduleSet = scheduleSet;
    }

    public Schedule(Airline airline, Airport origin, Airport destination, LocalDate startingDate, Integer days) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.startingDate = startingDate;
        this.days = days;
    }

    public Schedule(){

    }

    public LocalDate getNextDate(LocalDate currentDay) {
        for (LocalDate l: scheduleSet) {
            if (l.isAfter(currentDay)){
                return l;
            }
        }
        return currentDay.plusDays(1);
    }

}




