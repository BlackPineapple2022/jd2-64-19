package by.academy.it.travelcompany.travelcompany.dao.impl;

import by.academy.it.travelcompany.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.travelcompany.dao.DirectionDAO;
import by.academy.it.travelcompany.travelcompany.travelitem.direction.Direction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DirectionDAOImpl extends AbstractDAO implements DirectionDAO {

    private static final DirectionDAO INSTANCE = new DirectionDAOImpl();

    private DirectionDAOImpl() {
        super(LoggerFactory.getLogger(DirectionDAOImpl.class));
    }

    public static DirectionDAO getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_DIRECTION_BY_NAME = "SELECT * FROM direction WHERE direction_name=?";

//CRUD

    @Override
    public Long create(Direction direction) throws SQLException {
        return null;
    }

    @Override
    public Optional<Direction> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(Direction direction) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

//!CRUD

    @Override
    public List<Direction> getAll() throws SQLException {
        return null;
    }

    @Override
    public Long getIdByName(String name) throws SQLException {
        Long result = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DIRECTION_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
                return result;
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

}
