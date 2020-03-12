package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.robot.RYFlightScannerRobot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class MainController {

    @Autowired
    RYFlightScannerRobot ryFlightScannerRobot;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        log.debug("home controller");
        return "home";
    }

    @RequestMapping(value = "/startRY", method = RequestMethod.GET)
    public void startRY() {
        log.debug("ACTIVATE ROBOT RY");
        ryFlightScannerRobot.start();
    }
}
