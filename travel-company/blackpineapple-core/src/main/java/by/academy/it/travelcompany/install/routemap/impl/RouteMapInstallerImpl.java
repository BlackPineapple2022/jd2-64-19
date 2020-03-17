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
    private RouteMapService routeMapService;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private DirectionService directionService;
    @Autowired
    private AirportService airportService;

    @Override
    public void install() {
      log.info("Starting install route map");
        List<String> routeMapStringList = AirportInfoCentre.getRouteMapStringList(
                new ArrayList<>(AirportInfoCentre.getAllStartAirports()),
                new ArrayList<>(AirportInfoCentre.getAllAirportsFromStart())
        );

        for (String s : routeMapStringList) {
            String airlineName = s.split("--")[0];
            String originAirportCode = s.split("--")[1];
            String destinationAirportCode = s.split("--")[2];
            String directionName = s.split("--")[3];

            Airline airline = airlineService.getByName(airlineName);
            Airport origin = airportService.getByCode(originAirportCode);
            Airport destination = airportService.getByCode(destinationAirportCode);
            Direction direction = directionService.getByName(directionName);

            RouteMap routeMap = new RouteMap(airline, origin, destination, direction);
            routeMapService.create(routeMap);
        }
    }
}
