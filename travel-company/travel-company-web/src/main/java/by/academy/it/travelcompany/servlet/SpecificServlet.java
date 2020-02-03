package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.user.User;
import by.academy.it.travelcompany.user.favourite.Favourite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/specific")
public class SpecificServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Airport> allStartedAirports = AirportServiceImpl.getInstance().getAll();
        allStartedAirports.removeIf(a -> a.getCode().equals("VNO") || a.getCode().equals("KUN") ||
                a.getCode().equals("WAW") || a.getCode().equals("WMI"));
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.getId());
            if (favourites != null) {
                req.setAttribute("favourites", favourites);
            }
        }
        req.setAttribute("allStartedAirports", allStartedAirports);
        req.getRequestDispatcher("/WEB-INF/jsp/specific.jsp").forward(req, resp);
    }


}
