package by.academy.it.travelcompany.travelcompany.dao;

import by.academy.it.travelcompany.travelcompany.travelitem.airport.Airport;

import java.sql.SQLException;

public interface AirportDAO extends DAO<Airport> {

    Airport getByCode(String code) throws SQLException;

}
