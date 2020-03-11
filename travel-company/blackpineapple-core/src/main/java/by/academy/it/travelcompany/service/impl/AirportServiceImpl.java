package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.repository.AirportRepository;
import by.academy.it.travelcompany.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public void create(Airport airport) {
        log.info("Creating new airport" + airport);
        airportRepository.save(airport);
    }

    @Override
    public Airport getByCode(String code) {
        return airportRepository.getByCode(code);
    }
}
