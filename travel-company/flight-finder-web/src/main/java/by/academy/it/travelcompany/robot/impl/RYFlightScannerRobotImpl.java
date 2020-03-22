package by.academy.it.travelcompany.robot.impl;

import by.academy.it.travelcompany.scanner.FlightScanner;
import by.academy.it.travelcompany.FlightScannerData;
import by.academy.it.travelcompany.Schedule;
import by.academy.it.travelcompany.scanner.impl.RYFlightScanner;
import by.academy.it.travelcompany.provider.RouteMapStringProvider;
import by.academy.it.travelcompany.provider.ScheduleProvider;
import by.academy.it.travelcompany.robot.RYFlightScannerRobot;
import by.academy.it.travelcompany.sender.FlightSenderService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RYFlightScannerRobotImpl implements RYFlightScannerRobot {

    private Boolean isActive = false;

    @Autowired
    private ScheduleProvider scheduleProvider;
    @Autowired
    private RouteMapStringProvider routeMapStringProvider;
    @Autowired
    private FlightSenderService flightSenderService;

    @Override
    public void stop() {
        isActive = false;
    }

    @Override
    public void start(Integer dayCount, Long timeOut, Integer multiplier) {
        try{
            if (isActive) {
                return;
            }
            isActive = true;
            while (isActive) {

                log.info("RY Robot activated");
                String routeMapString = routeMapStringProvider.getRouteMapStringWithOlderScanningByAirline("RY");
                Schedule schedule = scheduleProvider.getScheduleByRouteMapString(routeMapString);
                FlightScannerData data = new FlightScannerData(routeMapString, dayCount, LocalDate.now());
                FlightScanner flightScanner = new RYFlightScanner(data, schedule);
                List<JSONObject> result = flightScanner.parse(timeOut, multiplier);
                flightSenderService.sendData(result);
            }
            isActive = false;
        }catch (Exception e ){
            isActive = false;
            log.error("Error while RY robot is working");
        }
    }

}
