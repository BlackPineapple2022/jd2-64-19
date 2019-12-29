package by.academy.it.travelcompany.service.local;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.flight.Flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FlightServiceImpl implements FlightService {

    private static final FlightService INSTANCE = new FlightServiceImpl();
    private final List<Flight> flights = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);

    private FlightServiceImpl() {

        Long id1 = 1L;
        Airport origin1 = new Airport("VNO");
        Airport destination1 = new Airport("BGY");
        LocalDateTime departureTime1 = LocalDateTime.of(2020, Month.APRIL, 6, 22, 25);
        LocalDateTime arriveTime1 = LocalDateTime.of(2020, Month.APRIL, 6, 23, 55);
        Airline airline1 = Airline.RY;
        Double ticketPrice1 = 21.84;//it is outdated price, let see how Flight scanner replace it;
        String flightN1 = "FR 2871";

        flights.add(new Flight(id1, origin1, destination1, departureTime1, arriveTime1, airline1, "EUR", ticketPrice1, flightN1));

        Long id2 = 2L;
        Airport origin2 = new Airport("BGY");
        Airport destination2 = new Airport("VNO");
        LocalDateTime departureTime2 = LocalDateTime.of(2019, Month.APRIL, 23, 17, 55);
        LocalDateTime arriveTime2 = LocalDateTime.of(2019, Month.APRIL, 23, 21, 20);
        Airline airline2 = Airline.RY;
        Double ticketPrice2 = 30.30;
        String flightN2 = "W6 8022";

        flights.add(new Flight(id2, origin2, destination2, departureTime2, arriveTime2, airline2, "EUR", ticketPrice2, flightN2));
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight addFlight(Flight flight) {
        flight.setId(sequence.incrementAndGet());
        flights.add(flight);
        return flight;
    }

    @Override
    public void deleteFlightById(Long id) {
        flights.removeIf(f -> f.getId().equals(id));
    }

    @Override
    public Flight updateFlightById(Flight flight) {
        deleteFlightById(flight.getId());
        flights.add(flight);
        return flight;
    }

    @Override
    public Flight updateOrCreate(Flight flight) throws IOException {
        if (flight == null
                || flight.getOriginAirport().getCode() == null
                || flight.getDestinationAirport().getCode() == null
                || flight.getArriveTime() == null
                || flight.getDepartureTime() == null
                || flight.getCurrency() == null
                || flight.getTicketPrice() == null) {
            throw new IOException("Any args has null value");
        }

        for (Flight f : flights) {
            if (f.equals(flight)) {
                flight.setId(f.getId());
                flights.remove(f);
                flights.add(flight);
                return flight;
            }
        }

        addFlight(flight);
        return flight;
    }

    @Override
    public List<Flight> getAllFlightBySearchIdAndDirection(Long searchId, String direction) {
        List<Flight> allFlight = getAllFlights();
        List<Flight> resultFlight = new ArrayList<>();
        for (Flight f : allFlight) {
            if (f.getSearchId()!=null && f.getDirection()!=null && f.getSearchId().equals(searchId) && f.getDirection().equals(direction)) {
                resultFlight.add(f);
            }
        }

        return resultFlight;

    }
}

