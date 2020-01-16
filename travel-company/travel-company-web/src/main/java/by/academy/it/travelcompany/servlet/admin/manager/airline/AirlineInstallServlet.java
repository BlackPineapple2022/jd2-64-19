package by.academy.it.travelcompany.servlet.admin.manager.airline;

import by.academy.it.travelcompany.service.global.AirlineService;
import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;
import by.academy.it.travelcompany.travelitem.airline.Airline;
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
@WebServlet(urlPatterns = "/admin/manager/airline/install")
public class AirlineInstallServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String install = req.getParameter("install");
        if (install.equals("INSTALL")){
            AirlineService airlineService = AirlineServiceImpl.getInstance();
            List<Airline> allAirline = airlineService.getAll();
            List<Long> allId = new ArrayList<>();
            for (Airline a: allAirline) {
                allId.add(a.getId());
            }
            for (Long l:allId){
                airlineService.delete(l);
            }
            airlineService.installAllAirline();
        }
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airline");
    }
}
