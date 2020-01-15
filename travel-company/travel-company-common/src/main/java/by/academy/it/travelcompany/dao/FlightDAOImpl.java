package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FlightDAOImpl extends AbstractDAO implements FlightDAO {

    private static final FlightDAO INSTANCE = new FlightDAOImpl();
    private FlightDAOImpl() {
        super(LoggerFactory.getLogger(UserDAOImpl.class));
    }
    public static FlightDAO getInstance() {
        return INSTANCE;
    }

    public static final String INSERT_FLIGHT = "INSERT INTO flight" +
            "(routemap_id, arrive_date_time, departure_date_time, flight_number, currency_id, price )" +
            "VALUE (?,?,?,?,?,?)";

    public static final String SELECT_ROUTEMAP = "SELECT * from routemap WHERE origin_airport_id = ? AND destination_airport_id = ? AND direction_id = ? AND airline_id = ?";

    public static final String SELECT_CURRENCY = "SELECT * from currency WHERE currency_code = ?";

    public static final String SELECT_AIRLINE = "SELECT * from airline WHERE airline_name = ?";
    public static final String SELECT_AIRPORT = "SELECT * from airport WHERE airport_code = ?";
    public static final String SELECT_DIRECTION = "SELECT * from direction WHERE direction_name = ?";
    public static final String SELECT_ALL_FLIGHT_WITH_SAME_ROUTE_MAP= "SELECT * FROM flight WHERE routemap_id = ?";







    @Override
    public Optional read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Long create(Flight flight) throws SQLException {
        String direction = flight.getDirection();
        String airline = flight.getAirline().toString();
        String originAirport = flight.getOriginAirport().getCode();
        String destinationAirport = flight.getDestinationAirport().getCode();
        String routeMap = airline+"--" +originAirport + "--" + destinationAirport +"--"+direction;
        int routeMapId = findIdRouteMap(routeMap);
        int currencyId = findCurrencyId(flight.getCurrency());
        LocalDateTime departureTime = flight.getDepartureTime();
        LocalDateTime arrivalTime = flight.getArriveTime();
        Double price = flight.getTicketPrice();
        String flightNumber = flight.getFlightNumber();

        ResultSet resultSet = null;
        Long result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FLIGHT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, routeMapId);
            statement.setString(2,arrivalTime.toString());
            statement.setString(3,departureTime.toString());
            statement.setString(4, flightNumber);
            statement.setInt(5, currencyId);
            statement.setString(6, price.toString());

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
    public Schedule getSchedule(RouteMap routemap) throws SQLException {
        String routeMapString = routemap.toString();
        int idRouteMap = findIdRouteMap(routeMapString);

        ResultSet resultSet = null;
        Set<LocalDate> result = new TreeSet<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FLIGHT_WITH_SAME_ROUTE_MAP)) {

            statement.setInt(1,idRouteMap);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String localDateTimeStr = resultSet.getString(4);
                String[] localDateTimeArr = localDateTimeStr.split(" ");
                String localDate = localDateTimeArr[0];
                String[] localDateArr = localDate.split("-");
                Integer year = Integer.parseInt(localDateArr[0]);
                Integer month = Integer.parseInt(localDateArr[1]);
                Integer day = Integer.parseInt(localDateArr[2]);
                LocalDate resultLocalDate = LocalDate.of(year,month,day);
                result.add(resultLocalDate);
            }
        } finally {
            closeQuietly(resultSet);
        }
        Schedule schedule = new Schedule(Airline.valueOf(routemap.getAirlineStr()),new Airport(routemap.getOriginAirportCode()),new Airport(routemap.getDestinationAirportCode()),LocalDate.now(),400);
        schedule.setScheduleSet(result);
        return schedule;
    }

    private int findIdRouteMap(String routeMap) throws SQLException {
        String[] routeArr = routeMap.split("--");
        int airlineId = findAirlineId(routeArr[0]);
        int airportOriginId = findAirportId(routeArr[1]);
        int airportDestinationId = findAirportId(routeArr[2]);
        int directionId = findDirectionId(routeArr[3]);
        ResultSet resultSet = null;
        int routeMapId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ROUTEMAP)) {
            statement.setInt(1,airportOriginId);
            statement.setInt(2,airportDestinationId);
            statement.setInt(3,directionId);
            statement.setInt(4,airlineId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                routeMapId = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return routeMapId;
    }

    private int findAirportId (String airportCode) throws SQLException {
        ResultSet resultSet = null;
        int airportId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRPORT)) {
            statement.setString(1,airportCode);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                airportId = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return airportId;
    }

    private int findDirectionId (String direction) throws SQLException {
        ResultSet resultSet = null;
        int directionId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DIRECTION)) {
            statement.setString(1,direction);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                directionId = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return directionId;
    }

    private int findAirlineId (String airline) throws SQLException {
        ResultSet resultSet = null;
        int airlineId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AIRLINE)) {
            statement.setString(1,airline);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                airlineId = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return airlineId;
    }

    private int findCurrencyId (String currency) throws SQLException {
        ResultSet resultSet = null;
        int currencyId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CURRENCY)) {
            statement.setString(1,currency);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                currencyId = resultSet.getInt(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return currencyId;
    }

}
