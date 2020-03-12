package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.repository.FlightJournalRepository;
import by.academy.it.travelcompany.service.FlightJournalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FlightJournalServiceImpl implements FlightJournalService {

    @Autowired
    FlightJournalRepository flightJournalRepository;

    @Override
    public void create(FlightJournal flightJournal) {
        log.info("Creating new flight journal");
        flightJournalRepository.save(flightJournal);
    }

    @Override
    public FlightJournal getByRouteMap(RouteMap routeMap) {
       log.info("Getting flight journal by route map");
       return flightJournalRepository.getByRouteMap(routeMap);
    }

    @Override
    public RouteMap getRouteMapByOlderOrNullableFlightJournal(String airlineName) {
        log.info("Getting route map with older or nullable flight journal");
        List<FlightJournal> list = flightJournalRepository.getAllByRouteMap_Airline_NameOrderByUpdatedDateTime(airlineName);
        for (FlightJournal flightJournal : list) {
            if (flightJournal.getUpdatedDateTime()==null){
                return flightJournal.getRouteMap();
            }
        }
        FlightJournal flightJournal = list.get(0);
        return flightJournal.getRouteMap();
    }
}
