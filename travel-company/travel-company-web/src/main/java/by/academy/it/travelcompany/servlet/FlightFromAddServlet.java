package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.AirportInfoCentre;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@WebServlet(urlPatterns = "/addFlightFrom")
public class FlightFromAddServlet extends HttpServlet {

    private FlightService flightService = FlightServiceImpl.getService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set allStartedAirports = AirportInfoCentre.getAllAirportsFromStart();
        //List<Airport> allAirport = new ArrayList<>(AirportInfoCentre.getAllAirports());
        req.setAttribute("allStartedAirports",allStartedAirports);
        req.getRequestDispatcher("/WEB-INF/addFlightFirstStep.jsp").
                forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Airport airportOrigin = new Airport(req.getParameter("airportOrigin"));
        if (req.getParameter("airportDestination") == null) {
            req.setAttribute("airportOrigin", airportOrigin);
            req.setAttribute("allDestinationAirports", AirportInfoCentre.getAllDestinations(airportOrigin));
            req.getRequestDispatcher("/WEB-INF/addFlightSecondStep.jsp").
                    forward(req, resp);
        }

    }
}