package by.academy.it.travelcompany.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.travelcompany.scanner.robot.FlightRobot;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebServlet(urlPatterns = "/admin/scanner/wizz/deactivate")
public class StopScannerWizzServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("STOPPING WIZZ ROBOT!");
        FlightRobot wizz = FlightRobot.getFlightRobotWIZZ();
        wizz.setActive(false);
        resp.sendRedirect(req.getContextPath()+"/about");
    }

}
