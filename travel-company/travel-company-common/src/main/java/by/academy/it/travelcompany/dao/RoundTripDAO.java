package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.trip.Trip;

import java.sql.SQLException;
import java.util.List;

public interface RoundTripDAO extends DAO<Trip> {

    List<Trip> getAllBySearchId(Long searchId) throws SQLException;

    void deleteAllBySearchIdButFavourite(List<Long> searchIdList) throws SQLException;

}
