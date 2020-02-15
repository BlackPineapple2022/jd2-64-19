package by.academy.it.travelcompany.servlet.admin.manager.airport;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet (urlPatterns = "/admin/manager/airport/install")
public class AirportInstallServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String install = req.getParameter("install");
        if (install.equals("INSTALL")){
            AirportServiceImpl airportService = AirportServiceImpl.getInstance();
            List<Airport> allAirport = airportService.getAll();

            List<Long> allId = new ArrayList<>();
            for (Airport a: allAirport) {
                allId.add(a.getId());
            }
            for (Long l:allId){
                airportService.delete(l);
            }
            airportService.installAllAirport();
        }
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airport");
    }
}
