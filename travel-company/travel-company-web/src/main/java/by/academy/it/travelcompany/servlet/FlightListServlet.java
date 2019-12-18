package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/flightList")
public class FlightListServlet extends HttpServlet {
    private FlightService flightService = FlightServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <Flight> flightList = flightService.getAllFlights();
        req.setAttribute("flightList",flightList);
        req.getRequestDispatcher("/WEB-INF/jsp/flight-list.jsp")
                .forward(req,resp);
    }

}
