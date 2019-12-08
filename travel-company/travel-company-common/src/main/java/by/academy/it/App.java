package by.academy.it;

import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.AirportInfoCentre;
import by.academy.it.travelcompany.airport.Airline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
//        System.out.println(AirportInfoCentre.getAllAirports());

        Map <Airline, Set<Airport>> airportMap = new HashMap<>();
        airportMap = AirportInfoCentre.getAllDestinationsAndCompany(new Airport("LIS","",""));
        Set <Airline> companies = airportMap.keySet();
        System.out.println(companies);
        Set <Airport> airportsRY = airportMap.get(Airline.RY);
        System.out.println("{RY} - " + airportsRY);
        Set <Airport> airportsWIZZ = airportMap.get(Airline.WIZZ);
        System.out.println("{WIZZ} - " + airportsWIZZ);
        //Airport bva = new Airport("BVA","dscvsdc","dfvfd");
        //Set <Airport> set = AirportInfoCentre.getAllAirportsFromVNORY();
        //System.out.println(set.contains(bva));
        //System.out.println(set);

        //Set <Airport> set2 = AirportInfoCentre.getAllAirportsFromWMIRY();
        //System.out.println(set2.contains(bva));

    }
}
