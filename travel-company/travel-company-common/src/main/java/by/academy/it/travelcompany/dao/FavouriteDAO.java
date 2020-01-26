package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.user.favourite.Favourite;

import java.sql.SQLException;
import java.util.List;

public interface FavouriteDAO extends DAO<Favourite> {

    int createDefaultFavouriteWhenCreatingNewUser(String userName, Long id) throws SQLException;

    Long createFavourite(String favouriteName, Long id) throws SQLException;

    List<Favourite> getAllByUserId(Long userId) throws SQLException;


}
