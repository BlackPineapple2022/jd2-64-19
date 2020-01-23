package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.dao.DAO;

import java.sql.SQLException;

public interface FlightScannerJournalDAO  {

    Long createJournalEntry(Long routeMapId) throws SQLException;


}
