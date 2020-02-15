package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightDAO extends DAO<Flight> {

    Schedule getSchedule(RouteMap routemap) throws SQLException;

    List<Flight> getAllFlightBySearchId(Long searchId) throws SQLException;

    int updateByDateAndFlightNumberOrCreate(Flight flight) throws SQLException;

    List<Flight> getFlightListByRouteMapIdAndDates(Long routemapId, LocalDate firstDate, LocalDate secondDate) throws SQLException;

}
