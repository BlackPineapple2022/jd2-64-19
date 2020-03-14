package by.academy.it.travelcompany.service;


import by.academy.it.travelcompany.entity.Airport;

import java.util.List;

public interface AirportService {

    void create(Airport airport);

    Airport getByCode(String code);

    List<Airport> getStartedAirportList();

}
