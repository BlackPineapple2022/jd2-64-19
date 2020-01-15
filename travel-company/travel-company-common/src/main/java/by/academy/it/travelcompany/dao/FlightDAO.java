package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;

import java.sql.SQLException;

public interface FlightDAO extends DAO<Flight> {

    Schedule getSchedule(RouteMap routemap) throws SQLException;
}
