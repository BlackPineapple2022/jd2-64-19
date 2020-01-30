package by.academy.it.travelcompany.dao;

import java.sql.SQLException;

public interface FavouriteListDAO {

    Long addTripToFavouriteList(Long favouriteId, Long tripId) throws SQLException;

    void deleteTripFromFavouriteList(Long favouriteId, Long tripId) throws SQLException;

}
