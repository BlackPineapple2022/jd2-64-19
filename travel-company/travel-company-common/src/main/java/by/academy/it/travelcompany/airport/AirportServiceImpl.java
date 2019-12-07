package by.academy.it.travelcompany.airport;

import java.util.HashSet;
import java.util.Set;

public class AirportServiceImpl implements AirportService {
    @Override
    public Set<Airport> getAllAirports() {
        Set<Airport> allAirports = new HashSet<>();
        for (int i = 0; i < AirportFromKUNRY.values().length; i++){
            allAirports.add(new Airport(AirportFromKUNRY.values()[i].toString(),AirportFromKUNRY.values()[i].getCountry(),AirportFromKUNRY.values()[i].getCity()));
        }
        for (int i = 0; i < AirportStart.values().length; i++){
            allAirports.add(new Airport(AirportStart.values()[i].toString(),AirportStart.values()[i].getCountry(),AirportStart.values()[i].getCity()));
        }

        return allAirports;
    }

    @Override
    public Set<Airport> getStartAirports() {
        return null;
    }

    @Override
    public Set<Airport> getSameCityAirports(Airport airport) {
        return null;
    }

    @Override
    public Set<Airport> getSameAreaAirports(Airport airport) {
        return null;
    }
}
