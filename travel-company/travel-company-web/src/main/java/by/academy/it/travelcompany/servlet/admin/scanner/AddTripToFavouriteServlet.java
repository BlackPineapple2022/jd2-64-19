package by.academy.it.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/favourite/add")
public class AddTripToFavouriteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newFavouriteName = req.getParameter("newFavourite");
        String whatFavouriteToAddTrip = req.getParameter("favourite");
        Long tripId = Long.parseLong(req.getParameter("tripId"));
        if ((!newFavouriteName.equals(""))) {
            whatFavouriteToAddTrip = user.getUserName()+"_"+newFavouriteName;
            FavouriteServiceImpl.getInstance().createFavourite(whatFavouriteToAddTrip, user.getId());

        }
        FavouriteServiceImpl.getInstance().addTripToFavourite(user.getId(),whatFavouriteToAddTrip,tripId);
    }

}
