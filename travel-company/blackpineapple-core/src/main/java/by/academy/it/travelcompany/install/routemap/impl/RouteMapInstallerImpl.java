package by.academy.it.travelcompany.install.routemap.impl;

import by.academy.it.travelcompany.entity.Airline;
import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.entity.Direction;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.install.airport.data.AirportInfoCentre;
import by.academy.it.travelcompany.install.routemap.RouteMapInstaller;
import by.academy.it.travelcompany.service.AirlineService;
import by.academy.it.travelcompany.service.AirportService;
import by.academy.it.travelcompany.service.DirectionService;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RouteMapInstallerImpl implements RouteMapInstaller {
    @Autowired
    RouteMapService routeMapService;
    @Autowired
    AirlineService airlineService;
    @Autowired
    DirectionService directionService;
    @Autowired
    AirportService airportService;

    @Override
    public void install() {
      log.info("Starting install route map");
        List<String> routeMapStringSet = AirportInfoCentre.getRouteMapStringList(
                new ArrayList<>(AirportInfoCentre.getAllStartAirports()),
                new ArrayList<>(AirportInfoCentre.getAllAirportsFromStart())
        );

        System.err.println(AirportInfoCentre.getAllStartAirports());
        System.err.println(AirportInfoCentre.getAllAirportsFromStart());

        for (String s : routeMapStringSet) {
            String airlineName = s.split("--")[0];
            String originAirportCode = s.split("--")[1];
            String destinationAirportCode = s.split("--")[2];
            String directionName = s.split("--")[3];

            Airline airline = airlineService.getByName(airlineName);
            Airport origin = airportService.getByCode(originAirportCode);
            Airport destination = airportService.getByCode(destinationAirportCode);
            Direction direction = directionService.getByName(directionName);

            RouteMap routeMap = new RouteMap(airline, origin, destination, direction);
            System.err.println(routeMap);
            //routeMapService.create(routeMap);
        }
    }
}
