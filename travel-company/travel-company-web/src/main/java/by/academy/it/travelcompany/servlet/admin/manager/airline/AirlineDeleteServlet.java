package by.academy.it.travelcompany.servlet.admin.manager.airline;

import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/manager/airline/delete")
public class AirlineDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        AirlineServiceImpl.getInstance().delete(id);
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airline");
    }
}
