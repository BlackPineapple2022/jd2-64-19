package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    private static final FlightService INSTANCE = new FlightServiceImpl();

    private final List <Flight> flights;

    private FlightServiceImpl(){
        flights = new ArrayList<>();
        // Manual adding flight
        long id1 = 1L;
        Airport origin1 = new Airport("VNO","Lithuania","Vilnius");
        Airport destination1 = new Airport ("BVA", "France","Paris Beauvais");
        LocalDateTime arriveTime1 = LocalDateTime.of(2019, Month.DECEMBER,19,15,30);
        LocalDateTime departureTime1 = LocalDateTime.of(2019,Month.DECEMBER,19,17,20);
        Airline airline1 = Airline.RY;
        double ticketPrice1 = 14.99;
        String flightN1 = "FR 1737";

        flights.add(new Flight(id1,origin1,destination1,arriveTime1,departureTime1,airline1,ticketPrice1,flightN1));

        long id2 = 2L;
        Airport origin2 = new Airport ("BVA", "France","Paris Beauvais");
        Airport destination2 = new Airport("VNO","Lithuania","Vilnius");
        LocalDateTime arriveTime2 = LocalDateTime.of(2019, Month.DECEMBER,22,17,10);
        LocalDateTime departureTime2 = LocalDateTime.of(2019,Month.DECEMBER,19,20,45);
        Airline airline2 = Airline.WIZZ;
        double ticketPrice2 = 194.99;
        String flightN2 = "W6 8022";

        flights.add(new Flight(id2,origin2,destination2,arriveTime2,departureTime2,airline2,ticketPrice2,flightN2));
    }

    public static FlightService getService(){
        return INSTANCE;
    }

    @Override
    public List<Flight> getAllFlights() {
        return  flights;
    }

    @Override
    public void addFlight(Flight flight) {
    flight.setId((long)flights.size()+1);
    flights.add(flight);
    }
}
