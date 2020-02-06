package by.academy.it.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.scanner.robot.FlightRobot;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        wizz.setDayCount(300);
        wizz.setActive(true);
        wizz.start();
    }
}
