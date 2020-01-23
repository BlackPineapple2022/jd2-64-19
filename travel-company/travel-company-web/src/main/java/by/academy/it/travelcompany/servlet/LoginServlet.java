package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.UserService;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.UserServiceImpl;
import by.academy.it.travelcompany.favourite.Favourite;
import by.academy.it.travelcompany.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String rememberMeStr = req.getParameter("rememberMe");
        boolean remeberMeboolean = "Y".equals(rememberMeStr);

        Integer errorCode = 0;
        boolean hasError = false;

        if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
            hasError = true;
            errorCode = 1;
        } else {
            Optional<User> user = userService.findUserByLoginAndPassword(userName, password);
            if (!user.isPresent()) {
                hasError = true;
                errorCode = 2;
            } else {

                req.getSession(true).setAttribute("user", user.get());

                List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.get().getId());
                req.getSession(true).setAttribute("favourites",favourites);
            }
        }

        if (hasError) {
            req.setAttribute("errorCode", errorCode);
            req.setAttribute("userName", userName);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }

    }
}
