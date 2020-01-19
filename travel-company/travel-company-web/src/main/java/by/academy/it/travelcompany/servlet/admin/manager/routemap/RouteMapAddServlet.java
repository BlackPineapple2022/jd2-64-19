package by.academy.it.travelcompany.servlet.admin.manager.routemap;

import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;
import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.imp.DirectionServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.direction.Direction;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/routemap/add")
public class RouteMapAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String originAirportCode = req.getParameter("originAirportCode");
        String destinationAirportCode = req.getParameter("destinationAirportCode");
        String airlineName = req.getParameter("airlineName");
        String directionName = req.getParameter("directionName");

        Airport originAirport = AirportServiceImpl.getInstance().getAirportByCode(originAirportCode);
        Airport destinationAirport = AirportServiceImpl.getInstance().getAirportByCode(destinationAirportCode);
        Airline airline = new Airline(AirlineServiceImpl.getInstance().getIdByName(airlineName),airlineName);
        Direction direction = new Direction(DirectionServiceImpl.getInstance().getIdByName(directionName),directionName);

        RouteMap routeMap = new RouteMap(null,airline,originAirport,destinationAirport,direction);

        RouteMapServiceImpl.getInstance().create(routeMap);
        resp.sendRedirect(req.getContextPath()+"/admin/manager/routemap");

    }
}
