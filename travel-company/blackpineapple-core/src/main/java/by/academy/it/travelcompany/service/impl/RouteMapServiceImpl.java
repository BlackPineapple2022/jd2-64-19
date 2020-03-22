package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.repository.RouteMapRepository;
import by.academy.it.travelcompany.service.RouteMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RouteMapServiceImpl implements RouteMapService {

    @Autowired
    private RouteMapRepository routeMapRepository;

    @Override
    public void create(RouteMap routeMap) {
        log.info("Creating new route map");
        routeMapRepository.save(routeMap);
    }

    @Override
    public List<RouteMap> getAll() {
        log.info("Getting all route map");
        return (List<RouteMap>) routeMapRepository.findAll();
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
        return routeMapRepository.findByAirline_NameAndAirportOrigin_CodeAndAirportDestination_Code(airline, origin, destination);
    }

    @Override
    public Set<RouteMap> getRouteMapSetByAirportCodeSets(
            Set<String> originAirportDirectCodeSet,
            Set<String> destinationAirportDirectCodeSet,
            Set<String> destinationAirportReturnCodeSet,
            Set<String> originAirportReturnCodeSet) {

        System.err.println("Getting set of route map by sets of code "+
                "originAirportDirectCodeSet" + originAirportDirectCodeSet +
                "destinationAirportDirectCodeSet" + destinationAirportDirectCodeSet +
                "destinationAirportReturnCodeSet" + destinationAirportReturnCodeSet +
                "originAirportReturnCodeSet" + originAirportReturnCodeSet

        );

        Set<RouteMap> result = new HashSet<>();

        for (String originDirect : originAirportDirectCodeSet) {
            for (String destinationDirect : destinationAirportDirectCodeSet) {
                List<RouteMap> r = routeMapRepository.findAllByAirportOrigin_CodeAndAirportDestination_Code(originDirect, destinationDirect);
                if (r != null) {
                    result.addAll(r);
                }
            }
        }

        for (String destinationReturn : destinationAirportDirectCodeSet) {
            for (String originReturn : originAirportReturnCodeSet) {
                List<RouteMap> r = routeMapRepository.findAllByAirportOrigin_CodeAndAirportDestination_Code(destinationReturn, originReturn);
                if (r != null) {
                    result.addAll(r);
                }
            }

        }
        return result;
    }
}



