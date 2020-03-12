package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.repository.RouteMapRepository;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteMapServiceImpl implements RouteMapService {

    @Autowired
    RouteMapRepository routeMapRepository;

    @Override
    public void create(RouteMap routeMap) {
        log.info("Creating new route map");
        routeMapRepository.save(routeMap);
    }

    @Override
    public List<RouteMap> getAll() {
        log.info("Getting all route map");
        return (List<RouteMap>)routeMapRepository.findAll();
    }

    @Override
    public Optional<RouteMap> getById(Serializable id) {
        log.info("Getting route map by id");
        return routeMapRepository.findById(id);
    }

    @Override
    public RouteMap getByRouteMapString(String routeMapString) {
       log.info("Getting route map by route map string " + routeMapString);
       String airline = routeMapString.split("--")[0];
       String origin = routeMapString.split("--")[1];
       String destination = routeMapString.split("--")[2];
       return routeMapRepository.findByAirline_NameAndAirportOrigin_CodeAndAirportDestination_Code(airline,origin,destination);
    }
}
