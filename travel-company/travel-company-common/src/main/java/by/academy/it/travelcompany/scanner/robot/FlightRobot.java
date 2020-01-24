package by.academy.it.travelcompany.scanner.robot;

import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.FlightScannerJournalServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Data
public class FlightRobot extends Thread{

    private static final RouteMapServiceImpl ROUTE_MAP_SERVICE = RouteMapServiceImpl.getInstance();
    private static final FlightScannerJournalServiceImpl FLIGHT_SCANNER_JOURNAL_SERVICE = FlightScannerJournalServiceImpl.getInstance();

    LocalDate startingDate;
    Integer dayCount;
    String airlineName;

    public FlightRobot(LocalDate startingDate, Integer dayCount, String airlineName) {
        this.startingDate = startingDate;
        this.dayCount = dayCount;
        this.airlineName=airlineName;
    }

    @Override
    public void run() {
        while(true){
            log.info("Getting routeMap id from journal where scanned date is null ");
            Long routeMapId = FLIGHT_SCANNER_JOURNAL_SERVICE.getFirstRouteMapIdWithNullDateTime(airlineName);
            if (routeMapId!=null){
               RouteMap routeMap = ROUTE_MAP_SERVICE.read(routeMapId).get();
               new FlightScannerImpl(routeMapId,routeMap,startingDate,dayCount).run();
               FLIGHT_SCANNER_JOURNAL_SERVICE.updateDateOnJournalEntry(routeMapId);
               continue;
            }
            routeMapId = FLIGHT_SCANNER_JOURNAL_SERVICE.getOlderRouteMapId(airlineName);
            if (routeMapId!=null){
                RouteMap routeMap = ROUTE_MAP_SERVICE.read(routeMapId).get();
                FLIGHT_SCANNER_JOURNAL_SERVICE.deleteDateOnJournalEntry(routeMapId);
                new FlightScannerImpl(routeMapId,routeMap,startingDate,dayCount).run();
                FLIGHT_SCANNER_JOURNAL_SERVICE.updateDateOnJournalEntry(routeMapId);
            }
        }
    }


}
