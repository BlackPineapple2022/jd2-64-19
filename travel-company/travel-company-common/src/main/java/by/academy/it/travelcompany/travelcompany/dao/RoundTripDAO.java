package by.academy.it.travelcompany.travelcompany.dao;

import by.academy.it.travelcompany.travelcompany.travelitem.trip.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoundTripDAO extends DAO<Trip> {

    List<Trip> getAllBySearchId(Long searchId) throws SQLException;

    void deleteAllBySearchIdButFavourite(List<Long> searchIdList) throws SQLException;

    Map<Long,List<Trip>> getFavouriteTripsByUserId(Long userId) throws SQLException;

    Optional<Trip> getTripWithOnlyFlightId(Long id) throws SQLException;

    void updatePrice(Long tripId, Double price) throws SQLException;

}
