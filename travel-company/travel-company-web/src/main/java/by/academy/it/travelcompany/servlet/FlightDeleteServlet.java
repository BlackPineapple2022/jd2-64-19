package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.local.FlightService;
import by.academy.it.travelcompany.service.local.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteFlight")
public class FlightDeleteServlet extends HttpServlet {
    private FlightService flightService = FlightServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("modelId") != null) {
            flightService.deleteFlightById(Long.parseLong(req.getParameter("modelId")));
        }

    }
}
