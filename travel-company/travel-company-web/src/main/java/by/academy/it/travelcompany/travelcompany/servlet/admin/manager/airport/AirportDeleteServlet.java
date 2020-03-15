package by.academy.it.travelcompany.travelcompany.servlet.admin.manager.airport;

import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/airport/delete")
public class AirportDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        AirportServiceImpl.getInstance().delete(id);
        resp.sendRedirect(req.getContextPath()+"/admin/manager/airport");
    }
}
