package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@WebServlet(urlPatterns = "/specificResult")
public class SpecificResultServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<String> originAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportReturnCodeSet = new HashSet<>();
        Set<String> originAirportReturnCodeSet = new HashSet<>();

        if (req.getParameter("originDirectIsVNO") != null && req.getParameter("originDirectIsVNO").equals("Y")) {
            originAirportDirectCodeSet.add("VNO");
        }
        if (req.getParameter("originDirectIsKUN") != null && req.getParameter("originDirectIsKUN").equals("Y")) {
            originAirportDirectCodeSet.add("KUN");
        }
        if (req.getParameter("originDirectIsWMI") != null && req.getParameter("originDirectIsWMI").equals("Y")) {
            originAirportDirectCodeSet.add("WMI");
        }
        if (req.getParameter("originDirectIsWAW") != null && req.getParameter("originDirectIsWAW").equals("Y")) {
            originAirportDirectCodeSet.add("WAW");
        }

        if (req.getParameter("originReturnIsVNO") != null && req.getParameter("originReturnIsVNO").equals("Y")) {
            originAirportReturnCodeSet.add("VNO");
        }
        if (req.getParameter("originReturnIsKUN") != null && req.getParameter("originReturnIsKUN").equals("Y")) {
            originAirportReturnCodeSet.add("KUN");
        }
        if (req.getParameter("originReturnIsWMI") != null && req.getParameter("originReturnIsWMI").equals("Y")) {
            originAirportReturnCodeSet.add("WMI");
        }
        if (req.getParameter("originReturnIsWAW") != null && req.getParameter("originReturnIsWAW").equals("Y")) {
            originAirportReturnCodeSet.add("WAW");
        }

        if (req.getParameter("airportDestinationDirect1") != null) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect1"));
        }
        if (req.getParameter("airportDestinationDirect2") != null) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect2"));
        }
        if (req.getParameter("airportDestinationDirect3") != null) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect3"));
        }
        if (req.getParameter("airportDestinationDirect4") != null) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect4"));
        }
        if (req.getParameter("airportDestinationDirect5") != null) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect5"));
        }

        if (req.getParameter("airportDestinationReturn1") != null) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn1"));
        }
        if (req.getParameter("airportDestinationReturn2") != null) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn2"));
        }
        if (req.getParameter("airportDestinationReturn3") != null) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn3"));
        }
        if (req.getParameter("airportDestinationReturn4") != null) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn4"));
        }
        if (req.getParameter("airportDestinationReturn5") != null) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn5"));
        }

        Set<RouteMap> routeMapSet = RouteMapServiceImpl.getInstance().getRouteMapSetByAirportCodeSets(
                originAirportDirectCodeSet, destinationAirportDirectCodeSet, destinationAirportReturnCodeSet, originAirportReturnCodeSet);

        String localDateStart = req.getParameter("startingDate");
        String localDateEnd = req.getParameter("endingDate");
        String regex = "-";
        String[] localDateArrStart = localDateStart.split(regex);
        String[] localDateArrEnd = localDateEnd.split(regex);

        LocalDate localDateStartL = LocalDate.of(Integer.parseInt(localDateArrStart[0]), Integer.parseInt(localDateArrStart[1]), Integer.parseInt(localDateArrStart[2]));
        LocalDate localDateEndL = LocalDate.of(Integer.parseInt(localDateArrEnd[0]), Integer.parseInt(localDateArrEnd[1]), Integer.parseInt(localDateArrEnd[2]));
        Integer min = Integer.parseInt(req.getParameter("minDay"));
        Integer max = Integer.parseInt(req.getParameter("maxDay"));

        Boolean isStartingSameAirport = false;
        Boolean isStartingSameCity = false;
        Boolean isStartingSameCountry = false;

        Boolean isEndingSameAirport = false;
        Boolean isEndingSameCity = false;
        Boolean isEndingSameCountry = false;

        String endingFilter = req.getParameter("endingFilter");
        String startingFilter = req.getParameter("startingFilter");

        switch (endingFilter) {
            case "airportFilter": {
                isEndingSameAirport = true;
                break;
            }
            case "cityFilter": {
                isEndingSameCity = true;
                break;
            }
            case "countryFilter": {
                isEndingSameCountry = true;
                break;
            }
        }

        switch (startingFilter) {
            case "airportFilter": {
                isStartingSameAirport = true;
                break;
            }
            case "cityFilter": {
                isStartingSameCity = true;
                break;
            }
            case "countryFilter": {
                isStartingSameCountry = true;
                break;
            }
        }


        System.out.println(routeMapSet);

        System.out.println(localDateStart);
        System.out.println(localDateEnd);

        System.out.println(min);
        System.out.println(max);

        System.out.println(isStartingSameAirport);
        System.out.println(isStartingSameCity);
        System.out.println(isStartingSameCountry);

        System.out.println(isEndingSameAirport);
        System.out.println(isEndingSameCity);
        System.out.println(isEndingSameCountry);


    }
}
