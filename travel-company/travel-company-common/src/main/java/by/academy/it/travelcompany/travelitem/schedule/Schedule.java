package by.academy.it.travelcompany.travelitem.schedule;

import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private AirlineEnum airlineEnum;
    private Airport origin;
    private Airport destination;
    private LocalDate startingDate;
    private Integer days;
    private Set<LocalDate> scheduleSet = new TreeSet<>();

    public AirlineEnum getAirlineEnum() {
        return airlineEnum;
    }

    public void setAirlineEnum(AirlineEnum airlineEnum) {
        this.airlineEnum = airlineEnum;
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

    public Schedule(AirlineEnum airlineEnum, Airport origin, Airport destination, LocalDate startingDate, Integer days) {
        this.airlineEnum = airlineEnum;
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




