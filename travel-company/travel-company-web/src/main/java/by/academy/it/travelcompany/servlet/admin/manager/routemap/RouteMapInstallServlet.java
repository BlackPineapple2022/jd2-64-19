package by.academy.it.travelcompany.servlet.admin.manager.routemap;

import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "/admin/manager/routemap/install")
public  class RouteMapInstallServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String install = req.getParameter("install");
        if (install.equals("INSTALL")){
            RouteMapServiceImpl routeMapService = RouteMapServiceImpl.getInstance();
            List<RouteMap> allRouteMap = routeMapService.getAll();
            List<Long> allId = new ArrayList<>();
            for (RouteMap r: allRouteMap) {
                allId.add(r.getId());
            }
            for (Long l:allId){
                routeMapService.delete(l);
            }
            routeMapService.installAllRouteMap();
        }
        resp.sendRedirect(req.getContextPath()+"/admin/manager/routemap");
    }
}
