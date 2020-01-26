package by.academy.it.travelcompany.servlet.admin.scanner;

import by.academy.it.travelcompany.scanner.tripscanner.TripScannerImpl;
import by.academy.it.travelcompany.service.global.imp.AirportServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.user.favourite.Favourite;
import by.academy.it.travelcompany.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/admin/scanner/manual")
@Slf4j
public class ManualScannerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Airport> allStartedAirports = AirportServiceImpl.getInstance().getAll();
        allStartedAirports.removeIf(a -> a.getCode().equals("VNO") || a.getCode().equals("KUN") ||
                a.getCode().equals("WAW") || a.getCode().equals("WMI"));
        User user = (User) req.getSession().getAttribute("user");
        List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.getId());
        req.setAttribute("favourites", favourites);
        req.setAttribute("allStartedAirports", allStartedAirports);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/manualScanner.jsp").forward(req, resp);
    }

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

        String localDate = req.getParameter("startingDate");
        String regex = "-";
        String[] localDateArr = localDate.split(regex);
        LocalDate localDateL = LocalDate.of(Integer.parseInt(localDateArr[0]), Integer.parseInt(localDateArr[1]), Integer.parseInt(localDateArr[2]));
        Integer deep = Integer.parseInt(req.getParameter("dayCount"));
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
        TripScannerImpl tripScanner = new TripScannerImpl(routeMapSet, localDateL, deep, min, max);
        List<Trip> trips = tripScanner.searchRoundTrip(isStartingSameCountry, isStartingSameCity, isStartingSameAirport, isEndingSameCountry, isEndingSameCity, isEndingSameAirport);

        System.out.println("hi 1");

        if (trips != null && trips.get(0) != null && trips.get(0).getFlights() != null && trips.get(0).getFlights().get(0) != null) {
            Long searchId = trips.get(0).getSearchId();
            System.out.println("1 " + searchId);
            List<Long> searchIdList = (List<Long>) req.getSession().getAttribute("searchIdList");
            System.out.println("2 " + searchIdList);
            searchIdList.add(searchId);
            System.out.println("3 " + searchIdList);
            req.getSession().setAttribute("searchIdList", searchIdList);
        }

        System.out.println("hi 2");

        req.setAttribute("trips", trips);
        User user = (User) req.getSession().getAttribute("user");
        List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.getId());
        req.setAttribute("favourites", favourites);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/manualScannerResultSearch.jsp").
                forward(req, resp);

    }
}
