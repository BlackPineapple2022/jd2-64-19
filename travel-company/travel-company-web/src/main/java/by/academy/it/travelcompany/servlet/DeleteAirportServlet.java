package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.AirportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/airportDelete")
public class DeleteAirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        AirportServiceImpl.getInstance().delete(id);
        resp.sendRedirect(req.getContextPath()+"/admin/airportList");
    }
}
