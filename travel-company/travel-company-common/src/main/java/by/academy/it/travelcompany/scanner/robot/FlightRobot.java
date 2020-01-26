package by.academy.it.travelcompany.scanner.robot;

import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.FlightScannerJournalServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class FlightRobot extends Thread {

    private static final FlightRobot FLIGHT_ROBOT_RY = new FlightRobot(250,"RY");
    private static final FlightRobot FLIGHT_ROBOT_WIZZ = new FlightRobot(250,"WIZZ");

    private static final RouteMapServiceImpl ROUTE_MAP_SERVICE = RouteMapServiceImpl.getInstance();
    private static final FlightScannerJournalServiceImpl FLIGHT_SCANNER_JOURNAL_SERVICE = FlightScannerJournalServiceImpl.getInstance();

    Integer dayCount;
    String airlineName;

    Boolean isActive = false;

    private FlightRobot(Integer dayCount, String airlineName) {
        this.dayCount = dayCount;
        this.airlineName = airlineName;
    }

    public static FlightRobot getFlightRobotRY() {
        return FLIGHT_ROBOT_RY;
    }

    public static FlightRobot getFlightRobotWIZZ() {
        return FLIGHT_ROBOT_WIZZ;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public void run() {
        while (true) {
            while (isActive) {
                log.info("Getting routeMap id from journal where scanned date is null ");
                Long routeMapId = FLIGHT_SCANNER_JOURNAL_SERVICE.getFirstRouteMapIdWithNullDateTime(airlineName);
                if (routeMapId != null) {
                    RouteMap routeMap = ROUTE_MAP_SERVICE.read(routeMapId).get();
                    new FlightScannerImpl(routeMapId, routeMap, LocalDate.now(), dayCount).run();
                    FLIGHT_SCANNER_JOURNAL_SERVICE.updateDateOnJournalEntry(routeMapId);
                    continue;
                }
                log.info("Getting routeMap id from journal where older scanned date");
                routeMapId = FLIGHT_SCANNER_JOURNAL_SERVICE.getOlderRouteMapId(airlineName);
                if (routeMapId != null) {
                    RouteMap routeMap = ROUTE_MAP_SERVICE.read(routeMapId).get();
                    FLIGHT_SCANNER_JOURNAL_SERVICE.deleteDateOnJournalEntry(routeMapId);
                    new FlightScannerImpl(routeMapId, routeMap, LocalDate.now(), dayCount).run();
                    FLIGHT_SCANNER_JOURNAL_SERVICE.updateDateOnJournalEntry(routeMapId);
                }
            }
            try {
                sleep(10*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
