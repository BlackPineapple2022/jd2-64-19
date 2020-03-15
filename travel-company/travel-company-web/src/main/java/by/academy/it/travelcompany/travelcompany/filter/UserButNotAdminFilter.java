package by.academy.it.travelcompany.travelcompany.filter;

import by.academy.it.travelcompany.travelcompany.user.User;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*", dispatcherTypes = DispatcherType.REQUEST)
public class UserButNotAdminFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if ((user != null && !user.getRole().equals("ADMIN"))){
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            super.doFilter(req, res, chain);
        }
    }
}
