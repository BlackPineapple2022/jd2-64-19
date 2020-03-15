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
@WebServlet (urlPatterns = "/admin/scanner/wizz/start")
public class StartScannerWizzServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("STARTING WIZZ ROBOT!");
        FlightRobot wizz = FlightRobot.getFlightRobotWIZZ();

        while (true){
            if (wizz.getWorking()){
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
        wizz.setDayCount(270);
        wizz.setActive(true);
        wizz.run();
        //resp.sendRedirect(req.getContextPath()+"/about");
    }
}
