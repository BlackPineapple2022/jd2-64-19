package by.academy.it.travelcompany.travelcompany.servlet.user;

import by.academy.it.travelcompany.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.travelcompany.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@WebServlet(urlPatterns = "/user/favourite/updateInfo")
public class UpdateFavouriteInfoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getId();
        Map<Long, List<Trip>> favouriteTrips = TripServiceImpl.getInstance().getFavouriteTripsByUserId(userId);
        Long favouriteId = FavouriteServiceImpl.getInstance().getIdByFavouriteName(user.getUserName() + "_favourite");
        List<Trip> tripList = favouriteTrips.get(favouriteId);

        if (tripList != null) {
            for (Trip t : tripList) {
                Double directFlightPrice = t.getFlights().get(0).getTicketPrice() *
                        CurrencyScannerImpl.getInstance().getEURMultiplier(t.getFlights().get(0).getCurrency().getCurrencyCode());

                Double returnFlightPrice = t.getFlights().get(1).getTicketPrice() *
                        CurrencyScannerImpl.getInstance().getEURMultiplier(t.getFlights().get(1).getCurrency().getCurrencyCode());
                t.setPrice(directFlightPrice + returnFlightPrice);

            }
        }

        req.setAttribute("trips", tripList);

        req.getRequestDispatcher("/WEB-INF/jsp/updateInfo.jsp").forward(req,resp);
    }
}
