package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.repository.AirportRepository;
import by.academy.it.travelcompany.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void create(Airport airport) {
        log.info("Creating new airport" + airport);
        airportRepository.save(airport);
    }

    @Override
    public Airport getByCode(String code) {
        return airportRepository.getByCode(code);
    }

    @Override
    public List<Airport> getStartedAirportList() {
        Iterable<Airport> airports = airportRepository.findAll();
        List<Airport> result = new ArrayList<>();
        for (Airport airport : airports) {
            if(
                    !airport.getCode().equals("VNO")||
                    !airport.getCode().equals("KUN")||
                    !airport.getCode().equals("WAW")||
                    !airport.getCode().equals("WMI")
            ){
                result.add(airport);
            }
        }
        return result;
    }
}
