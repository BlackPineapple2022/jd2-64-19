package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Airline;

import java.io.Serializable;

public interface AirlineService {

    void create(Airline airline);

    Airline getByName(String code);

}
