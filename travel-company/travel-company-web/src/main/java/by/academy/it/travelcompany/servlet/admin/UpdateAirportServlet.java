package by.academy.it.travelcompany.servlet.admin;

import by.academy.it.travelcompany.service.global.AirportServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/updateAirport")
public class UpdateAirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/updateAirport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airportCode = req.getParameter("airportCode");
        String airportCountry = req.getParameter("country");
        String airportCity = req.getParameter("city");
        Long id = Long.parseLong(req.getParameter("id"));
        Airport newAirport = new Airport(id,airportCode,airportCountry,airportCity);
        AirportServiceImpl.getInstance().update(newAirport);
        req.getRequestDispatcher("/WEB-INF/jsp/updateAirport.jsp").forward(req, resp);
    }
}
