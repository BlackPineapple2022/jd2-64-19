package by.academy.it.travelcompany.servlet.admin.favourite;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.user.User;
import by.academy.it.travelcompany.user.favourite.Favourite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/admin/favourite")
public class FavouriteTripServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getId();
        Map<Long, List<Trip>> favouriteTrips = TripServiceImpl.getInstance().getFavouriteTripsByUserId(userId);
        Map <String,List<Trip>> favouriteTripsByFavouriteName = new HashMap<>();
        List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(userId);
        for (Favourite f:favourites ) {
            favouriteTripsByFavouriteName.put(f.getFavouriteName(),favouriteTrips.get(f.getId()));
        }

        req.setAttribute("favouriteTripMap", favouriteTripsByFavouriteName);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/favouriteTrips.jsp").
                forward(req, resp);
    }
}
