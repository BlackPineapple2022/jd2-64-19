package by.academy.it.travelcompany.servlet.admin.install;

import by.academy.it.travelcompany.dao.RouteMapDAO;
import by.academy.it.travelcompany.dao.RouteMapDAOImpl;
import by.academy.it.travelcompany.service.global.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet(urlPatterns = "/admin/install/addAllRouteMapToBase")
public class AddAllRouteMapToBase extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RouteMapServiceImpl routeMapServiceImpl = RouteMapServiceImpl.getInstance();
        Set<String> routeMapArr = AirportInfoCentre.getRouteMap(
                new ArrayList<>(AirportInfoCentre.getAllStartAirports()),
                new ArrayList<>(AirportInfoCentre.getAllAirportsFromStart())
        );
        int i = 0;
        for(String str: routeMapArr){
            routeMapServiceImpl.addToBase(str);
            i++;
        }
        System.out.println(i);
        req.getRequestDispatcher("/WEB-INF/jsp/adminpanel.jsp").forward(req,resp);
    }
}
