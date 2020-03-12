package by.academy.it.travelcompany.install.flightjournal.impl;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.install.flightjournal.FlightJournalInstaller;
import by.academy.it.travelcompany.service.FlightJournalService;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FlightJournalInstallerImpl implements FlightJournalInstaller {

    @Autowired
    FlightJournalService flightJournalService;

    @Autowired
    RouteMapService routeMapService;

    @Override
    public void install() {
        log.info("Starting install flight journal");
        List<RouteMap> routeMapList = routeMapService.getAll();
        for (RouteMap routeMap : routeMapList) {
            flightJournalService.create(new FlightJournal(routeMap,null));
        }
    }
}
