package by.academy.it.travelcompany.servlet.admin.manager.airport;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet (urlPatterns = "/admin/manager/airport")
public class AirportManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("airportList", AirportServiceImpl.getInstance().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/airportManager.jsp").forward(req,resp);
    }
}
