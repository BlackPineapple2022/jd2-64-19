package by.academy.it.travelcompany.servlet.admin.manager.routemap;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/routemap")
public class RouteMapManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("routeMapList", RouteMapServiceImpl.getInstance().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/routeMapManager.jsp").forward(req,resp);
    }
}
