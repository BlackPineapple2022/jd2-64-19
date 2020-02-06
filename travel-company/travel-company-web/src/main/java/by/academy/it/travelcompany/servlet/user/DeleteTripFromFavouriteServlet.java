package by.academy.it.travelcompany.servlet.user;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/user/favourite/delete")
public class DeleteTripFromFavouriteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        Long tripId = Long.parseLong(req.getParameter("tripId"));
        String favouriteName = user.getUserName()+"_favourite";
        Long favouriteId = FavouriteServiceImpl.getInstance().getIdByFavouriteName(favouriteName);
        FavouriteServiceImpl.getInstance().deleteTripFromFavouriteList(favouriteId,tripId);
    }
}
