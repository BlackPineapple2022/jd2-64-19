package by.academy.it.travelcompany.travelcompany.servlet.admin.favourite;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/favourite/deleteTrip")
public class DeleteTripServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tripId = Long.parseLong(req.getParameter("tripId"));
        String favouriteName = req.getParameter("favouriteName");
        Long favoriteId = FavouriteServiceImpl.getInstance().getIdByFavouriteName(favouriteName);
        FavouriteServiceImpl.getInstance().deleteTripFromFavouriteList(favoriteId,tripId);
        resp.sendRedirect(req.getContextPath()+"/admin/favourite");
    }
}
