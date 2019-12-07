package by.academy.it;

import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.AirportFromKUNRY;
import by.academy.it.travelcompany.airport.AirportService;
import by.academy.it.travelcompany.airport.AirportServiceImpl;

import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //System.out.println(AirportFromKUNRY.values()[0].toString());
       /* Airport airport = new Airport(AirportFromKUNRY.values()[0].toString(),AirportFromKUNRY.values()[0].getCountry(),AirportFromKUNRY.values()[0].getCity());
        System.out.println(airport.toString());
    */
        AirportService airportService = new AirportServiceImpl();
        Set<Airport> allAirports = airportService.getAllAirports();
        for (Airport a: allAirports ) {
            System.out.println(a);
        }

    }
}
