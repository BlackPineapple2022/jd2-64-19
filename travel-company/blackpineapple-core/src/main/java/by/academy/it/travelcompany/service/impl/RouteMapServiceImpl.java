package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.repository.RouteMapRepository;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
