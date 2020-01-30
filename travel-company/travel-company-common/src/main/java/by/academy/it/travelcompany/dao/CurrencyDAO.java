package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.currency.Currency;

import java.sql.SQLException;

public interface CurrencyDAO extends DAO<Currency> {

    Long getIdByCode(String code) throws SQLException;

}
