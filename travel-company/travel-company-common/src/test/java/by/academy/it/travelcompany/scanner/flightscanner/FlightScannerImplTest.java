package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FlightScannerImplTest {

    @Test
    public void getReqStringRY() {
        FlightScanner scanner = FlightScannerImpl.getInstance();
        scanner.parseFlights(Airline.RY,10,new Airport("VNO"),new Airport("BGY"));
        //System.out.println(scanner.getReqStringRY(LocalDate.now(),new Airport("VNO"),new Airport("BGY")));
    }
}