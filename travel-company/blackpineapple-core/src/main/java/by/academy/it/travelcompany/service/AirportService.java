package by.academy.it.travelcompany.service;


import by.academy.it.travelcompany.entity.Airport;

public interface AirportService {

    void create(Airport airport);

    Airport getByCode(String code);

}
