package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightScannerImplTest {

    @Test
    public void parseFlights() {
        FlightScanner scanner = FlightScannerImpl.getInstance();
        try {
            scanner.parseFlights(Airline.WIZZ, LocalDate.of(2020, 7, 1), 12, new Airport("VNO"), new Airport("MXP"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlightService flightService = FlightServiceImpl.getInstance();
        List<Flight> flights = flightService.getAllFlights();
        /*for (Flight f : flights) {
            System.out.println(f);
        }*/
    }
}


   /* @Test
    public void parseFlights3() {

        FlightScanner scanner = FlightScannerImpl.getInstance();
        try {
            scanner.parseFlights(Airline.WIZZ, LocalDate.of(2019,12,21),5,new Airport("VNO"),new Airport("MXP"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/




