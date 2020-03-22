package by.academy.it.travelcompany.install.airline.impl;

import by.academy.it.travelcompany.entity.Airline;
import by.academy.it.travelcompany.install.airline.AirlineInstaller;
import by.academy.it.travelcompany.install.airline.data.AirlineEnum;
import by.academy.it.travelcompany.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirlineInstallerImpl implements AirlineInstaller {
    @Autowired
    private AirlineService airlineService;

    @Override
    public void install(){
        log.info("Starting installing airline");
        for (AirlineEnum a: AirlineEnum.values()) {
            airlineService.create(new Airline(a.name()));
        }
    }
}
