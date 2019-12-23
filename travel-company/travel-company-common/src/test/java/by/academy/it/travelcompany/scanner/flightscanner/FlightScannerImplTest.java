package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightScannerImplTest {

    @Test
    public void parseFlights() {
        FlightScanner scanner = new FlightScannerImpl();
        try {
            scanner.parseFlightsWIZZ(LocalDate.of(2019,12,26),31,new Airport("VNO"),new Airport("MXP"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}






