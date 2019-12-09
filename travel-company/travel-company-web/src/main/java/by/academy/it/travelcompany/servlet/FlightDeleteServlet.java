package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteFlight")
public class FlightDeleteServlet extends HttpServlet {
    private FlightService flightService = FlightServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("modelId") != null) {
            flightService.deleteFlight(Long.parseLong(req.getParameter("modelId")));
        }

    }
}
