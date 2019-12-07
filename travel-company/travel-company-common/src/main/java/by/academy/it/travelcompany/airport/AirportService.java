package by.academy.it.travelcompany.airport;

import java.util.Set;

public interface AirportService {

    Set <Airport> getAllAirports();
    Set <Airport> getStartAirports();
    Set <Airport> getSameCityAirports(Airport airport);
    Set <Airport> getSameAreaAirports(Airport airport);


}
