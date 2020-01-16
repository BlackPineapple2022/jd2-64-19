package by.academy.it.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/admin/scanner/startFlightScannerRY")
public class StartFlightScannerRYServlet extends HttpServlet {
    private static Boolean isScannerFree = true;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isScannerFree) {
            return;
        }
        isScannerFree = false;
        String originStr = req.getParameter("origin");
        String destinationStr = req.getParameter("destination");
        String dayCountStr = req.getParameter("dayCount");
        Integer dayCountInt = Integer.parseInt(dayCountStr);
        RouteMap routeMapDirect = new RouteMap("RY",originStr,destinationStr,"Direct");
        RouteMap routeMapReturn = new RouteMap("RY",originStr,destinationStr,"Return");
        FlightScannerImpl flightScannerDirect = new FlightScannerImpl(null, AirlineEnum.RY,new Airport(originStr),new Airport (destinationStr), LocalDate.now(),dayCountInt,"Direct");
        FlightScannerImpl flightScannerReturn = new FlightScannerImpl(null, AirlineEnum.RY,new Airport (destinationStr), new Airport(originStr), LocalDate.now(),dayCountInt,"Return");
        flightScannerDirect.start();
        flightScannerReturn.start();
    }
}
