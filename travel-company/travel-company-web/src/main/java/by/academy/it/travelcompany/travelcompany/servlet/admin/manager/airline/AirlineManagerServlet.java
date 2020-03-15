package by.academy.it.travelcompany.travelcompany.servlet.admin.manager.airline;

import by.academy.it.travelcompany.service.global.imp.AirlineServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/manager/airline")
@Slf4j
public class AirlineManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("airlineList", AirlineServiceImpl.getInstance().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/airlineManager.jsp").forward(req,resp);
    }

}
