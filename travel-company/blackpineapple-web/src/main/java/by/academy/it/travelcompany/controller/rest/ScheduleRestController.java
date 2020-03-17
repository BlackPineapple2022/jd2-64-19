package by.academy.it.travelcompany.controller.rest;

import by.academy.it.travelcompany.dto.ScheduleDTO;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "schedule/")
public class ScheduleRestController {

    @Autowired
    private RouteMapService routeMapService;
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/{routemap}")
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable("routemap") String routeMapString) {

        log.info("REST: getting schedule for route map " + routeMapString);

        RouteMap r = routeMapService.getByRouteMapString(routeMapString);
        List<Flight> flightList = flightService.getAllByRouteMap(r);
        List<String> localDateStringList = new ArrayList<>();

        for (Flight flight : flightList) {
            LocalDate localDate = flight.getDepartureDateTime().toLocalDate();
            localDateStringList.add(localDate.toString());
        }

        ScheduleDTO dto = new ScheduleDTO(localDateStringList);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
