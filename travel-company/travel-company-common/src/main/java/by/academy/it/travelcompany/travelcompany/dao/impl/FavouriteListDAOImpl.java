package by.academy.it.travelcompany.travelcompany.dao.impl;

import by.academy.it.travelcompany.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.travelcompany.dao.FavouriteListDAO;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class FavouriteListDAOImpl extends AbstractDAO implements FavouriteListDAO {

    private static final FavouriteListDAO INSTANCE = new FavouriteListDAOImpl();

    private FavouriteListDAOImpl() {
        super(LoggerFactory.getLogger(FavouriteListDAOImpl.class));
    }

    public static FavouriteListDAO getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_FAVOURITE_LIST = "INSERT INTO favourite_list (favourite_id, trip_id) VALUE (?,?)";
    private static final String DELETE_FAVOURITE_LIST = "DELETE FROM favourite_list WHERE favourite_id = ? AND trip_id = ?";

    @Override
    public Long addTripToFavouriteList(Long favouriteId, Long tripId) throws SQLException {
        Long result = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FAVOURITE_LIST, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, favouriteId);
            statement.setLong(2, tripId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
                return result;
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public void deleteTripFromFavouriteList(Long favouriteId, Long tripId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FAVOURITE_LIST)) {
                 statement.setLong(1,favouriteId);
                 statement.setLong(2,tripId);
                 statement.executeUpdate();
        }
    }


}
