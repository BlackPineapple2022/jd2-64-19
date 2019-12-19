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
            scanner.parseFlights(Airline.RY, LocalDate.of(2020,4,6),1,new Airport("VNO"),new Airport("BGY"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlightService flightService = FlightServiceImpl.getInstance();
        List<Flight> flights = flightService.getAllFlights();
        for (Flight f:flights) {
            if (f.getArriveTime().equals(LocalDateTime.of(2020,4,6,22,25))){
                assertNotEquals(f.getTicketPrice(),21.84);
                break;
            }
        }
    }

    @Test
    public void parseFlights1() {
        if (LocalDate.now().isBefore(LocalDate.of(2020,1,1))) {
            FlightService flightService = FlightServiceImpl.getInstance();
            List<Flight> flights = flightService.getAllFlights();
            int startSizeArr = flights.size();

            FlightScanner scanner = FlightScannerImpl.getInstance();
            try {
                scanner.parseFlights(Airline.RY, LocalDate.of(2020, 1, 1), 2, new Airport("VNO"), new Airport("BGY"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(flights.size(), startSizeArr + 3);
        }
    }

    @Test
    public void parseFlights2() {

        FlightService flightService = FlightServiceImpl.getInstance();
        List<Flight> flights = flightService.getAllFlights();
        int startSizeArr = flights.size();

        FlightScanner scanner = FlightScannerImpl.getInstance();
        try {
            scanner.parseFlights(Airline.RY, LocalDate.of(2020,2,1),5,new Airport("KUN"),new Airport("BGY"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Flight f: flights ) {
            assertNotEquals(f.getOriginAirport(),new Airport("KUN"));
        }
        assertEquals(flights.size(),startSizeArr);
    }

    @Test
    public void parseFlights3() {

        FlightScanner scanner = FlightScannerImpl.getInstance();
        try {
            scanner.parseFlights(Airline.WIZZ, LocalDate.of(2019,12,21),5,new Airport("VNO"),new Airport("MXP"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}