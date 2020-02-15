package by.academy.it.travelcompany.servlet.user;

import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteTripUpdaterServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Thread.sleep;
@Slf4j
@WebServlet(urlPatterns = "/user/favourite/update")
public class UpdateFavouriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        User user = (User)req.getSession().getAttribute("user");

        Boolean free = FavouriteTripUpdaterServiceImpl.getInstance().isFreeForUserId(user.getId());

        if (!free){
          while(!FavouriteTripUpdaterServiceImpl.getInstance().isFreeForUserId(user.getId())){
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  log.error("Error while thread sleep");
              }
          }
          resp.sendRedirect(req.getContextPath()+"/user/favourite");
        }
        else {
            FavouriteTripUpdaterServiceImpl.getInstance().startUpdater(user.getId());
            Long favouriteId = FavouriteServiceImpl.getInstance().getIdByFavouriteName(user.getUserName() + "_favourite");
            List<Trip> trips = TripServiceImpl.getInstance().getFavouriteTripsByUserId(user.getId()).get(favouriteId);

            for (Trip trip : trips) {

                Long tripId = trip.getId();

                Long directFlightId = trip.getFlights().get(0).getId();
                Long returnFlightId = trip.getFlights().get(1).getId();

                Flight directFlight = FlightServiceImpl.getInstance().read(directFlightId).get();
                Flight returnFlight = FlightServiceImpl.getInstance().read(returnFlightId).get();

                RouteMap directFlightRouteMap = directFlight.getRouteMap();
                RouteMap returnFlightRouteMap = returnFlight.getRouteMap();

                Long directFlightSearchId = directFlight.getSearchId();
                Long returnFlightSearchId = returnFlight.getSearchId();

                LocalDate directFlightDate = directFlight.getDepartureTime().toLocalDate();
                LocalDate returnFlightDate = returnFlight.getDepartureTime().toLocalDate();

                FlightScannerImpl directScanner = new FlightScannerImpl(directFlightSearchId, directFlightRouteMap, directFlightDate, 0);
                FlightScannerImpl returnScanner = new FlightScannerImpl(returnFlightSearchId, returnFlightRouteMap, returnFlightDate, 0);

                directScanner.start();
                returnScanner.start();

                while (directScanner.isAlive() || returnScanner.isAlive()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        log.error("Interrupted while sleeping");
                    }
                }

                Trip newTrip = TripServiceImpl.getInstance().getTripById(tripId);

                directFlightId = newTrip.getFlights().get(0).getId();
                returnFlightId = newTrip.getFlights().get(1).getId();

                directFlight = FlightServiceImpl.getInstance().read(directFlightId).get();
                returnFlight = FlightServiceImpl.getInstance().read(returnFlightId).get();

                Double directFlightPriceEUR = directFlight.getTicketPrice() *
                        CurrencyScannerImpl.getInstance().getEURMultiplier(directFlight.getCurrency().getCurrencyCode());
                Double returnFlightPriceEUR = returnFlight.getTicketPrice() *
                        CurrencyScannerImpl.getInstance().getEURMultiplier(returnFlight.getCurrency().getCurrencyCode());
                TripServiceImpl.getInstance().updatePrice(tripId, directFlightPriceEUR + returnFlightPriceEUR);


            }
            FavouriteTripUpdaterServiceImpl.getInstance().finishUpdater(user.getId());
            resp.sendRedirect(req.getContextPath() + "/user/favourite");
        }
    }
}
