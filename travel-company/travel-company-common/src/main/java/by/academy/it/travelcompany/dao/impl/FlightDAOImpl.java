package by.academy.it.travelcompany.dao.impl;

import by.academy.it.travelcompany.dao.AbstractDAO;
import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public static final String INSERT_FLIGHT = "INSERT INTO flight" +
            "(routemap_id, departure_date_time,arrive_date_time, flight_number, currency_id, price, search_id)" +
            "VALUE (?,?,?,?,?,?,?)";

    public static final String SELECT_ALL_FLIGHT_WITH_SAME_ROUTE_MAP = "SELECT * FROM flight WHERE routemap_id = ?";



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
            statement.setString(3, arrivalTime.toString() );
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
    public Optional read(Long id) throws SQLException {
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

    @Override
    public List getAll() throws SQLException {
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











}
