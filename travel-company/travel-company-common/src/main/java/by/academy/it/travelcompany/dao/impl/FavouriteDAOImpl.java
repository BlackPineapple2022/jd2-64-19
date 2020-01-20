package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.DirectionDAO;
import by.academy.it.travelcompany.dao.FavouriteDAO;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.user.Favourite;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FavouriteDAOImpl extends AbstractDAO implements FavouriteDAO {
    private static final FavouriteDAO INSTANCE = new FavouriteDAOImpl();

    private FavouriteDAOImpl() {
        super(LoggerFactory.getLogger(FavouriteDAOImpl.class));
    }

    public static FavouriteDAO getInstance() {
        return INSTANCE;
    }

    public static String INSERT_NEW_USER = "INSERT INTO favourite (user_id,favourite_name) VALUE (?,?)";
    public static String SELECT_FAVOURITE = "SELECT * FROM favourite WHERE user_id = ?";

    @Override
    public Long create(Favourite favourite) throws SQLException {
        return null;
    }

    @Override
    public Optional<Favourite> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(Favourite favourite) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<Favourite> getAll() throws SQLException {
        return null;
    }

    @Override
    public int newUser(String userName, Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)) {
            statement.setLong(1, id);
            statement.setString(2, userName+"_favourite");
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Favourite> getAllByUserId(Long userId) throws SQLException {
        ResultSet resultSet = null;
        List<Favourite> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FAVOURITE)) {
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Favourite(resultSet.getLong(1),resultSet.getString(2)));
            }
        }finally {
            closeQuietly(resultSet);
        }
        return result;
    }
}
