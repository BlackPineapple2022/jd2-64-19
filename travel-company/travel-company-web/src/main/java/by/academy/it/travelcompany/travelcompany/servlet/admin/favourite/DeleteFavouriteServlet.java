package by.academy.it.travelcompany.travelcompany.servlet.admin.favourite;

import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/favourite/deleteFavourite")
public class DeleteFavouriteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String favouriteName = req.getParameter("favouriteName");
        Long userId = Long.parseLong(req.getParameter("userId"));
        FavouriteServiceImpl.getInstance().deleteFavouriteByNameAndUserId(favouriteName,userId);
        resp.sendRedirect(req.getContextPath()+"/admin/favourite");
    }
}
