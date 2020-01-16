package by.academy.it.travelcompany.servlet.admin.manager.airline;

import by.academy.it.travelcompany.dao.impl.AirlineDAOImpl;
import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/airline/update")
public class AirlineUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String airlineName = req.getParameter("airlineName");
        Airline airline = new Airline(null,airlineName);
        airline.setId(id);
        AirlineServiceImpl.getInstance().update(airline);
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airline");
    }
}
