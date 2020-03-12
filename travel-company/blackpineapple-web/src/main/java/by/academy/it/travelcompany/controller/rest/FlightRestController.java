package by.academy.it.travelcompany.controller.rest;

import by.academy.it.travelcompany.converter.dtotoentity.FlightConverter;
import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "flights")
@Slf4j
public class FlightRestController {
    @Autowired
    FlightService flightService;
    @Autowired
    FlightConverter flightConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createOrUpdateFlight(
            @RequestBody FlightDTO flightDTO) {

        log.info("REST: start creating or updating flight " +flightDTO);
        Flight flight = flightConverter.convert(flightDTO);
        flightService.createOrUpdate(flight);
    }
}
