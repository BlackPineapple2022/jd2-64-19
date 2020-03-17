package by.academy.it.travelcompany.controller.rest;

import by.academy.it.travelcompany.converter.dtotoentity.FlightConverter;
import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.service.FlightJournalService;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "flights/finder/")
@Slf4j
public class FlightRestController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightConverter flightConverter;
    @Autowired
    private RouteMapService routeMapService;
    @Autowired
    private FlightJournalService flightJournalService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createOrUpdateFlight(
            @RequestBody List<FlightDTO> flightDTOs) {

        log.info("REST: start creating or updating flights " + flightDTOs);

        for (FlightDTO flightDTO : flightDTOs) {
            Flight flight = flightConverter.convert(flightDTO);
            flightService.createOrUpdate(flight);
        }

        if (flightDTOs.size() > 0) {
            FlightDTO flightDTO = flightDTOs.get(0);
            RouteMap routeMap = routeMapService.getByRouteMapString(flightDTO.getRouteMap());
            FlightJournal flightJournal = flightJournalService.getByRouteMap(routeMap);
            flightJournal.setUpdatedDateTime(LocalDateTime.now());
            flightJournalService.create(flightJournal);
        }

    }
}
