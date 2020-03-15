package by.academy.it.travelcompany.travelcompany.dao;

import by.academy.it.travelcompany.travelcompany.travelitem.airline.Airline;

import java.sql.SQLException;

public interface AirlineDAO extends DAO<Airline> {

    Long getIdByName(String name) throws SQLException;

}
