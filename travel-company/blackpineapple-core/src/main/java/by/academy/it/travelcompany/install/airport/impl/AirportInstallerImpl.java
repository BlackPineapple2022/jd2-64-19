package by.academy.it.travelcompany.install.airport.impl;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.install.airport.AirportInstaller;
import by.academy.it.travelcompany.install.airport.data.AirportInfoCentre;
import by.academy.it.travelcompany.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Slf4j
public class AirportInstallerImpl implements AirportInstaller {

    @Autowired
    private AirportService airportService;

    @Override
    public void install() {
        log.info("Starting to install airport");
        Set<Airport> airportSet = AirportInfoCentre.getAllAirports();
        for (Airport airport : airportSet) {
            airportService.create(airport);
        }
    }
}
