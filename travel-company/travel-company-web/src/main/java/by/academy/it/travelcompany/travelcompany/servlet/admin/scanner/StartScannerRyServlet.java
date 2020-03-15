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
@WebServlet(urlPatterns = "/admin/scanner/ry/start")
public class StartScannerRyServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("STARTING RY ROBOT!");
        FlightRobot ry = FlightRobot.getFlightRobotRY();

        while (true){
            if (ry.getWorking()){
                try {
                    Thread.sleep(10000*60);
                } catch (InterruptedException e) {
                    log.error("robot was interrupted");
                }

            }
            else{
                break;
            }
        }
        ry.setDayCount(270);
        ry.setActive(true);
        ry.run();
        //resp.sendRedirect(req.getContextPath()+"/about");
    }
}
