package by.academy.it.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.scanner.robot.FlightRobot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/scanner/wizz/deactivate")
public class StopScannerWizzServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FlightRobot wizz = FlightRobot.getFlightRobotWIZZ();
        wizz.setActive(false);

    }

}
