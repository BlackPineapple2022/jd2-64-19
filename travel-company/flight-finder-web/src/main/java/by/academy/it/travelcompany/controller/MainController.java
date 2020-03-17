package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.robot.RYFlightScannerRobot;
import by.academy.it.travelcompany.robot.WIZZFlightScannerRobot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private RYFlightScannerRobot ryFlightScannerRobot;

    @Autowired
    private WIZZFlightScannerRobot wizzFlightScannerRobot;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        log.debug("home controller");
        return "home";
    }

    @RequestMapping(value = "/startRY", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void startRYScanner(
            @RequestParam("dayCount") String dayCount,
            @RequestParam("timeout") String timeout,
            @RequestParam("multiplier") String multiplier) {

        log.info("Activate RY robot");
        ryFlightScannerRobot.start(Integer.parseInt(dayCount),Long.parseLong(timeout),Integer.parseInt(multiplier));
    }

    @RequestMapping(value = "/stopRY", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void stopRYScanner() {
        log.info("Stop RY robot");
        ryFlightScannerRobot.stop();
    }

    @RequestMapping(value = "/startWIZZ", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void startWIZZScanner(
            @RequestParam("dayCount") String dayCount,
            @RequestParam("timeout") String timeout,
            @RequestParam("multiplier") String multiplier) {

        log.info("Activate WIZZ robot");
        wizzFlightScannerRobot.start(Integer.parseInt(dayCount),Long.parseLong(timeout),Integer.parseInt(multiplier));
    }

    @RequestMapping(value = "/stopWIZZ", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void stopWIZZScanner() {
        log.info("Stop WIZZ robot");
        wizzFlightScannerRobot.stop();
    }

    @RequestMapping(value = "/wizzapi", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void changeWizzAPI(
            @RequestParam("apiAddress") String apiAddress) {

        log.info("Change api address" + apiAddress);
        wizzFlightScannerRobot.updateApiAddress(apiAddress);
    }
}
