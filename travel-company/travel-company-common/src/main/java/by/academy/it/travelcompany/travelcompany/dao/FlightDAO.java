package by.academy.it.travelcompany.travelcompany.dao;

import by.academy.it.travelcompany.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelcompany.travelitem.schedule.Schedule;
import by.academy.it.travelcompany.travelcompany.travelitem.routemap.RouteMap;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface FlightDAO extends DAO<Flight> {

    Schedule getSchedule(RouteMap routemap) throws SQLException;

    List<Flight> getAllFlightBySearchId(Long searchId) throws SQLException;

    int updateByDateAndFlightNumberOrCreate(Flight flight) throws SQLException;

    List<Flight> getFlightListByRouteMapIdAndDates(Long routemapId, LocalDate firstDate, LocalDate secondDate) throws SQLException;

}
