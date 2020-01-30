package by.academy.it.travelcompany.dao;

import java.sql.SQLException;

public interface FlightScannerJournalDAO {

    Long createJournalEntry(Long routeMapId) throws SQLException;

    void deleteCheckedDateTimeOnJournalEntry(Long routeMapId) throws SQLException;

    void updateDateTimeOnJournalEntry(Long routeMapId) throws SQLException;

    Long getRouteMapIdWithNullDateTime(String airlineName) throws SQLException;

    Long getRouteMapIdWithOlderDateTime(String airlineName) throws SQLException;

}
