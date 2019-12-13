package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FlightServiceImpl implements FlightService {

    private static final FlightService INSTANCE = new FlightServiceImpl();
    private final List<Flight> flights = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);

    private FlightServiceImpl() {
//                                               !!!---IT IS REAL PRICE, REAL FLIGHT, REAL DATE, REAL TIME---!!!
        Long id1 = 1L;
        Airport origin1 = new Airport("VNO");
        Airport destination1 = new Airport("BGY");
        LocalDateTime arriveTime1 = LocalDateTime.of(2019, Month.APRIL, 6, 22, 25);
        LocalDateTime departureTime1 = LocalDateTime.of(2019, Month.APRIL, 6, 23, 55);
        Airline airline1 = Airline.RY;
        double ticketPrice1 = 21.84;
        String flightN1 = "FR 2871";

        flights.add(new Flight(id1, origin1, destination1, arriveTime1, departureTime1, airline1, ticketPrice1, flightN1));

        Long id2 = 2L;
        Airport origin2 = new Airport("BGY");
        Airport destination2 = new Airport("VNO");
        LocalDateTime arriveTime2 = LocalDateTime.of(2019, Month.APRIL, 23, 17, 55);
        LocalDateTime departureTime2 = LocalDateTime.of(2019, Month.APRIL, 23, 21, 20);
        Airline airline2 = Airline.RY;
        double ticketPrice2 = 30.30;
        String flightN2 = "W6 8022";

        flights.add(new Flight(id2, origin2, destination2, arriveTime2, departureTime2, airline2, ticketPrice2, flightN2));
    }

    public static FlightService getService() {
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
    public void deleteFlight(Long id) {
        flights.removeIf(f -> f.getId().equals(id));
    }

    @Override
    public Flight updateFlight(Flight flight) {
        deleteFlight(flight.getId());
        flights.add(flight);
        return flight;
    }

}
