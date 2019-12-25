package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.service.local.FlightService;
import by.academy.it.travelcompany.service.local.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/addFlightTo")
public class FlightToAddServlet extends HttpServlet {

    private FlightService flightService = FlightServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set allStartedAirports = AirportInfoCentre.getAllStartAirports();
        req.setAttribute("allStartedAirports",allStartedAirports);
        req.getRequestDispatcher("/WEB-INF/jsp/addFlightFirstStep.jsp").
                forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Airport airportOrigin = new Airport(req.getParameter("airportOrigin"));
        if (req.getParameter("airportDestination") == null) {
            req.setAttribute("airportOrigin", airportOrigin);
            req.setAttribute("allDestinationAirports", AirportInfoCentre.getAllDestinations(airportOrigin));
            req.getRequestDispatcher("/WEB-INF/jsp/addFlightSecondStep.jsp").
                    forward(req, resp);
        }

    }
}
