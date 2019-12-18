package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightScannerImplTest {

    @Test
    public void parseFlights() {
        FlightScanner scanner = FlightScannerImpl.getInstance();
        scanner.parseFlights(Airline.RY, LocalDate.of(2020,4,6),1,new Airport("VNO"),new Airport("BGY"));
        FlightService flightService = FlightServiceImpl.getInstance();
        List<Flight> flights = flightService.getAllFlights();
        for (Flight f:flights) {
            if (f.getArriveTime().equals(LocalDateTime.of(2020,4,6,22,25))){
                assertNotEquals(f.getTicketPrice(),21.84);
                break;
            }
        }

    }
}