package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Airline;
import by.academy.it.travelcompany.repository.AirlineRepository;
import by.academy.it.travelcompany.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public void create(Airline airline) {
        log.info("Creating new airline: " + airline);
        airlineRepository.save(airline);
    }

    @Override
    public Airline getByName(String name) {
        return airlineRepository.getByName(name);
    }
}
