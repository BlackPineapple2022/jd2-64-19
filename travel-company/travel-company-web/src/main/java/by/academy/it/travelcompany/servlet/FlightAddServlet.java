package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.service.local.FlightService;
import by.academy.it.travelcompany.service.local.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet (urlPatterns = "/addFlight")
public class FlightAddServlet extends HttpServlet {

    private FlightService flightService = FlightServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set allStartedAirports = AirportInfoCentre.getAllAirports();
        req.setAttribute("allStartedAirports",allStartedAirports);
        req.getRequestDispatcher("/WEB-INF/jsp/addFlightFirstStep.jsp").
            forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Airport airportOrigin = new Airport (req.getParameter("airportOrigin"));
        if (req.getParameter("airportDestination")==null) {
            req.setAttribute("airportOrigin", airportOrigin);
            req.setAttribute("allDestinationAirports",AirportInfoCentre.getAllDestinations(airportOrigin));
            req.getRequestDispatcher("/WEB-INF/jsp/addFlightSecondStep.jsp").
                    forward(req, resp);
        }else{

            Airport airportDestination = new Airport(req.getParameter("airportDestination"));
            Airline airline = Airline.valueOf(req.getParameter("airlines"));

            String arriveTime = req.getParameter("arriveTime");
            String[] arriveTimeArr = arriveTime.split("-");
            LocalDateTime arriveTimeL = LocalDateTime.of(Integer.parseInt(arriveTimeArr[0]),Integer.parseInt(arriveTimeArr[1]),
                    Integer.parseInt(arriveTimeArr[2]),Integer.parseInt(arriveTimeArr[3]),Integer.parseInt(arriveTimeArr[4]));

            String departureTime = req.getParameter("departureTime");
            String[] departureTimeArr = arriveTime.split("-");
            LocalDateTime departureTimeL = LocalDateTime.of(Integer.parseInt(departureTimeArr[0]),Integer.parseInt(departureTimeArr[1]),
                    Integer.parseInt(departureTimeArr[2]),Integer.parseInt(departureTimeArr[3]),Integer.parseInt(departureTimeArr[4]));

            double ticketPrice = Double.parseDouble(req.getParameter("ticketPrice"));
            String flightNumber = req.getParameter("flightNumber");


            Flight flight = new Flight(null,airportOrigin,airportDestination,arriveTimeL,departureTimeL,airline,"EUR",ticketPrice,flightNumber);
            flightService.addFlight(flight);

            resp.sendRedirect(req.getContextPath() + "/flightList");
        }
    }
}
