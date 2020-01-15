package by.academy.it.travelcompany.servlet.admin.install;

import by.academy.it.travelcompany.service.global.AirportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/admin/install/addAllAirportToBase")
public class AddAllAirportToBase extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AirportServiceImpl.getInstance().addAllAirportToBase();
        req.getRequestDispatcher("/WEB-INF/jsp/adminpanel.jsp").forward(req,resp);
    }
}
