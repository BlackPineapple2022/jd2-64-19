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

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/airport/add")
public class AirportAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airportCode = req.getParameter("airportCode");
        String airportCountry = req.getParameter("airportCountry");
        String airportCity = req.getParameter("airportCity");
        AirportServiceImpl airportService = AirportServiceImpl.getInstance();
        airportService.create(new Airport(null,airportCode,airportCountry,airportCity));
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airport");
    }
}
