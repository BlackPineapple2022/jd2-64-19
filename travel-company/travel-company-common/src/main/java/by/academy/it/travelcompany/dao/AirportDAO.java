package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.airport.Airport;

import java.sql.SQLException;

public interface AirportDAO extends DAO<Airport> {

    Airport getAirportByCode(String code) throws SQLException;

}
