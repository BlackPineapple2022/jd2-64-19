package by.academy.it.travelcompany.servlet.admin.favourite;

import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.lang.Thread.sleep;

@Slf4j
@WebServlet(urlPatterns = "/admin/favourite/updateTrip")
public class UpdateTripServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tripId = Long.parseLong(req.getParameter("tripId"));
        Trip trip = TripServiceImpl.getInstance().getTripById(tripId);
        Long directFlightId = trip.getFlights().get(0).getId();
        Long returnFlightId = trip.getFlights().get(1).getId();

        Flight directFlight = FlightServiceImpl.getInstance().read(directFlightId).get();
        Flight returnFlight = FlightServiceImpl.getInstance().read(returnFlightId).get();

        RouteMap directFlightRouteMap = directFlight.getRouteMap();
        RouteMap returnFlightRouteMap = returnFlight.getRouteMap();

        Long directFlightSearchId = directFlight.getSearchId();
        Long returnFlightSearchId = returnFlight.getSearchId();

        LocalDate directFlightDate = directFlight.getArriveTime().toLocalDate();
        LocalDate returnFlightDate = returnFlight.getArriveTime().toLocalDate();

        FlightScannerImpl directScanner = new FlightScannerImpl(directFlightSearchId, directFlightRouteMap, directFlightDate, 1);
        FlightScannerImpl returnScanner = new FlightScannerImpl(returnFlightSearchId, returnFlightRouteMap, returnFlightDate, 1);

        directScanner.start();
        returnScanner.start();

        while(directScanner.isAlive()||returnScanner.isAlive()){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                log.error("Interrupted while sleeping");
            }
        }

        trip = TripServiceImpl.getInstance().getTripById(tripId);
        directFlightId = trip.getFlights().get(0).getId();
        returnFlightId = trip.getFlights().get(1).getId();

        directFlight = FlightServiceImpl.getInstance().read(directFlightId).get();
        returnFlight = FlightServiceImpl.getInstance().read(returnFlightId).get();

        Double directFlightPriceEUR = directFlight.getTicketPrice() *
                CurrencyScannerImpl.getInstance().getEURMultiplier(directFlight.getCurrency().getCurrencyCode());
        Double returnFlightPriceEUR = returnFlight.getTicketPrice() *
                CurrencyScannerImpl.getInstance().getEURMultiplier(returnFlight.getCurrency().getCurrencyCode());
        TripServiceImpl.getInstance().updatePrice(tripId,directFlightPriceEUR+returnFlightPriceEUR);
        resp.sendRedirect(req.getContextPath() + "/admin/favourite");
    }


}
