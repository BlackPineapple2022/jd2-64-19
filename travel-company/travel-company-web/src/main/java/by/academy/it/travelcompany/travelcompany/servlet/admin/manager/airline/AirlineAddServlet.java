package by.academy.it.travelcompany.travelcompany.servlet.admin.manager.airline;

import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;
import by.academy.it.travelcompany.travelcompany.travelitem.airline.Airline;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/airline/add")
public class AirlineAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airlineName = req.getParameter("airlineName");
        AirlineServiceImpl.getInstance().create(new Airline(null,airlineName));
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airline");
    }
}
