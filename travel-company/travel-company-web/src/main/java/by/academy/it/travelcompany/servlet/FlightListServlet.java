package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.service.local.FlightServiceLocal;
import by.academy.it.travelcompany.service.local.FlightServiceLocalImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/flightList")
public class FlightListServlet extends HttpServlet {
    private FlightServiceLocal flightServiceLocal = FlightServiceLocalImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <Flight> flightList = flightServiceLocal.getAllFlights();
        req.setAttribute("flightList",flightList);
        req.getRequestDispatcher("/WEB-INF/jsp/flight-list.jsp")
                .forward(req,resp);
    }

}
