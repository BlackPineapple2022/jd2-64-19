package by.academy.it.travelcompany.controller.rest;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.service.FlightJournalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "flightjournals/finder/")
public class FlightJournalRestController {

    @Autowired
    FlightJournalService flightJournalService;

    @GetMapping(value = "/{airline}")
    public ResponseEntity<String> getRouteMap(@PathVariable("airline") String airline) {

        log.info("REST: getting route map for " + airline);

        RouteMap r = flightJournalService.getRouteMapByOlderOrNullableFlightJournal(airline);
        String result = r.toString();
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

}
