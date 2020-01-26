package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.CurrencyDAO;
import by.academy.it.travelcompany.travelitem.currency.Currency;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CurrencyDAOImpl extends AbstractDAO implements CurrencyDAO {

    private static final CurrencyDAO INSTANCE = new CurrencyDAOImpl();

    private CurrencyDAOImpl() {
        super(LoggerFactory.getLogger(CurrencyDAOImpl.class));
    }

    public static CurrencyDAO getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_CURRENCY_BY_CODE = "SELECT * FROM currency WHERE currency_code = ?";

//CRUD

    @Override
    public Long create(Currency currency) throws SQLException {
        return null;
    }

    @Override
    public Optional<Currency> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(Currency currency) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

//!CRUD

    @Override
    public List<Currency> getAll() throws SQLException {
        return null;
    }

    @Override
    public Long getIdByCode(String code) throws SQLException {
        Long result = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CURRENCY_BY_CODE)) {
            statement.setString(1, code);
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
