package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.UserService;
import by.academy.it.travelcompany.service.global.imp.UserServiceImpl;
import by.academy.it.travelcompany.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/signup")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");

        Integer errorCode = 0;
        boolean hasError = false;

        if (userName == null || userName.length() == 0 || password == null || password.length() == 0 || passwordRepeat == null || passwordRepeat.length() == 0) {
            hasError = true;
            errorCode = 1;
        } else if (!userService.isUserNameFree(userName)) {
            hasError = true;
            errorCode = 3;
        } else if (!password.equals(passwordRepeat)) {
            hasError = true;
            errorCode = 4;
        } else {
            userService.create(new User(null,userName,password));
        }

        if (hasError) {
            req.setAttribute("errorCode", errorCode);
            req.setAttribute("userName", userName);
            req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
