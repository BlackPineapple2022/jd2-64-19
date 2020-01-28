package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.currency.Currency;
import by.academy.it.travelcompany.travelitem.direction.Direction;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
public class FlightDAOImpl extends AbstractDAO implements FlightDAO {

    private static final FlightDAO INSTANCE = new FlightDAOImpl();

    private FlightDAOImpl() {
        super(LoggerFactory.getLogger(UserDAOImpl.class));
    }

    public static FlightDAO getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_FLIGHT = "INSERT INTO flight" +
            "(routemap_id, departure_date_time,arrive_date_time, flight_number, currency_id, price, search_id)" +
            "VALUE (?,?,?,?,?,?,?)";
    private static final String SELECT_FLIGHT =
            "SELECT f.id,r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name,f.departure_date_time,f.arrive_date_time,f.flight_number,f.price,c.id,c.currency_code,f.checked_date, f.search_id" +
                    " FROM flight f JOIN routemap r ON f.routemap_id = r.id JOIN currency c ON f.currency_id = c.id JOIN airport apo ON r.origin_airport_id = apo.id " +
                    " JOIN airport apd ON r.destination_airport_id = apd.id JOIN airline al ON r.airline_id = al.id JOIN direction d ON r.direction_id = d.id WHERE f.id = ?";

    private static final String SELECT_ALL_FLIGHT_WITH_SAME_ROUTE_MAP = "SELECT * FROM flight WHERE routemap_id = ?";

    private static final String SELECT_ALL_FLIGHT_WITH_SAME_SEARCH_ID =
            "SELECT f.id,r.id,al.id,al.airline_name,apo.id,apo.airport_code,apo.country,apo.city,apd.id,apd.airport_code,apd.country,apd.city,d.id,d.direction_name,f.departure_date_time,f.arrive_date_time,f.flight_number,f.price,c.id,c.currency_code,f.checked_date,f.search_id" +
                    " FROM flight f JOIN routemap r ON f.routemap_id = r.id JOIN currency c ON f.currency_id = c.id JOIN airport apo ON r.origin_airport_id = apo.id " +
                    " JOIN airport apd ON r.destination_airport_id = apd.id JOIN airline al ON r.airline_id = al.id JOIN direction d ON r.direction_id = d.id WHERE f.search_id = ?";

    private static final String UPDATE_BY_DATE_AND_FLIGHT_NUMBER = "UPDATE flight SET price = ?, checked_date = now(),search_id = ? WHERE flight_number = ? AND departure_date_time LIKE ?";

//CRUD

    @Override
    public Long create(Flight flight) throws SQLException {
        Long routeMapId = flight.getRouteMap().getId();
        LocalDateTime departureTime = flight.getDepartureTime();
        LocalDateTime arrivalTime = flight.getArriveTime();
        String flightNumber = flight.getFlightNumber();
        Long currencyId = flight.getCurrency().getId();
        Double price = flight.getTicketPrice();
        Long searchId = flight.getSearchId();

        ResultSet resultSet = null;
        Long result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FLIGHT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, routeMapId);
            statement.setString(2, departureTime.toString());
            statement.setString(3, arrivalTime.toString());
            statement.setString(4, flightNumber);
            statement.setLong(5, currencyId);
            statement.setString(6, price.toString());
            statement.setLong(7, searchId);

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional<Flight> read(Long id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FLIGHT)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Flight result = mapFlight(resultSet);
                return Optional.of(result);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public int update(Flight flight) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

//!CRUD

    @Override
    public List<Flight> getAll() throws SQLException {
        return null;
    }

    @Override
    public Schedule getSchedule(RouteMap routeMap) throws SQLException {
        Long id = routeMap.getId();
        ResultSet resultSet = null;
        Set<LocalDate> result = new TreeSet<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FLIGHT_WITH_SAME_ROUTE_MAP)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String localDateTimeStr = resultSet.getString(3);
                String[] localDateTimeArr = localDateTimeStr.split(" ");
                String localDate = localDateTimeArr[0];
                String[] localDateArr = localDate.split("-");
                Integer year = Integer.parseInt(localDateArr[0]);
                Integer month = Integer.parseInt(localDateArr[1]);
                Integer day = Integer.parseInt(localDateArr[2]);
                LocalDate resultLocalDate = LocalDate.of(year, month, day);
                result.add(resultLocalDate);
            }
        } finally {
            closeQuietly(resultSet);
        }
        Schedule schedule = new Schedule(routeMap);
        schedule.setScheduleSet(result);
        return schedule;

    }

    @Override
    public List<Flight> getAllFlightBySearchId(Long searchId) throws SQLException {
        ResultSet resultSet = null;
        List<Flight> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FLIGHT_WITH_SAME_SEARCH_ID)) {

            statement.setLong(1, searchId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Flight f = mapFlight(resultSet);
                result.add(f);
            }

        } finally {
            closeQuietly(resultSet);
        }
        return result;

    }

    private Flight mapFlight(ResultSet resultSet) throws SQLException {
        Long flightId = resultSet.getLong(1);
        Long routeMapId = resultSet.getLong(2);
        Long airlineId = resultSet.getLong(3);
        String airlineName = resultSet.getString(4);
        Long airportOriginId = resultSet.getLong(5);
        String airportOriginCode = resultSet.getString(6);
        String airportOriginCountry = resultSet.getString(7);
        String airportOriginCity = resultSet.getString(8);
        Long airportDestinationId = resultSet.getLong(9);
        String airportDestinationCode = resultSet.getString(10);
        String airportDestinationCountry = resultSet.getString(11);
        String airportDestinationCity = resultSet.getString(12);
        Long directionId = resultSet.getLong(13);
        String directionName = resultSet.getString(14);
        String departureDateTimeStr = resultSet.getString(15);
        String arriveDateTimeStr = resultSet.getString(16);
        String flightNumber = resultSet.getString(17);
        Double price = resultSet.getDouble(18);
        Long currencyId = resultSet.getLong(19);
        String currencyCode = resultSet.getString(20);
        String checkedTimeStr = resultSet.getString(21);
        Long searchId = resultSet.getLong(22);

        RouteMap routeMap = new RouteMap(
                routeMapId,
                new Airline(airlineId, airlineName),
                new Airport(airportOriginId, airportOriginCode, airportOriginCountry, airportOriginCity),
                new Airport(airportDestinationId, airportDestinationCode, airportDestinationCountry, airportDestinationCity),
                new Direction(directionId, directionName)
        );

        Currency currency = new Currency(currencyId, currencyCode);
        LocalDateTime departureDateTime = getLocalDateTimeFromString(departureDateTimeStr, " ", "-", ":");
        LocalDateTime arriveDateTime = getLocalDateTimeFromString(arriveDateTimeStr, " ", "-", ":");
        LocalDateTime checkedTime = getLocalDateTimeFromString(checkedTimeStr, " ", "-", ":");

        Flight f = new Flight(flightId, routeMap, departureDateTime, arriveDateTime, currency, price, flightNumber);
        f.setCheckedTime(checkedTime);
        f.setSearchId(searchId);
        return f;
    }

    @Override
    public int updateByDateAndFlightNumberOrCreate(Flight flight) throws SQLException {
        int result = -1;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_DATE_AND_FLIGHT_NUMBER)) {
            statement.setDouble(1, flight.getTicketPrice());
            statement.setLong(2, flight.getSearchId());
            statement.setString(3, flight.getFlightNumber());
            statement.setString(4, flight.getDepartureTime().toLocalDate().toString() + "%");
            result = statement.executeUpdate();
            if (result == 0) {
                create(flight);
            }
        }
        return result;
    }

    private LocalDateTime getLocalDateTimeFromString(String str, String regexDateFromTime, String regexDayMonthYear, String regexMinHour) {
        String date = str.split(regexDateFromTime)[0];
        String year = date.split(regexDayMonthYear)[0];
        String month = date.split(regexDayMonthYear)[1];
        String day = date.split(regexDayMonthYear)[2];
        String time = str.split(regexDateFromTime)[1];
        String hour = time.split(regexMinHour)[0];
        String min = time.split(regexMinHour)[1];
        return LocalDateTime.of(
                LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)),
                LocalTime.of(Integer.parseInt(hour), Integer.parseInt(min)));
    }

}
