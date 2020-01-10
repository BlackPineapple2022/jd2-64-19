package by.academy.it.travelcompany.servlet.admin;

import by.academy.it.travelcompany.service.global.AirportServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/addAirport")
public class AddAirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/addAirport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airportCode = req.getParameter("airportCode");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        AirportServiceImpl airportService = AirportServiceImpl.getInstance();
        airportService.addToBase(new Airport(airportCode,country,city));
        req.getRequestDispatcher("/WEB-INF/jsp/addAirport.jsp").forward(req,resp);

    }
}
