package by.academy.it.travelcompany.dao;

import java.sql.SQLException;

public interface FlightScannerJournalDAO  {

    Long createJournalEntry(Long routeMapId) throws SQLException;

    void deleteCheckedDate(Long routeMapId) throws SQLException;

    void updateJournalEntry(Long routeMapId) throws SQLException;

    Long getFirstNullDateTime(String airlineName) throws SQLException;

    Long getOlder(String airlineName) throws SQLException;


}
