package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.scanner.tripscanner.TripScannerImpl;
import by.academy.it.travelcompany.service.local.TripServiceLocal;
import by.academy.it.travelcompany.service.local.TripServiceLocalImpl;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.travelitem.trip.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/spec")
public class SpecificFlightSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("searchId") != null) {
            TripServiceLocal tripServiceLocal = TripServiceLocalImpl.getInstance();
            List<Trip> tripList = tripServiceLocal.getAllTripBySearchId(Long.parseLong(req.getParameter("searchId")));
            req.setAttribute("trips", tripList);
            req.getRequestDispatcher("/WEB-INF/jsp/resultSearch.jsp").
                    forward(req, resp);
        } else {
            Set<Airport> allStartedAirports = AirportInfoCentre.getAllAirportsFromStart();
            req.setAttribute("allStartedAirports", allStartedAirports);
            req.getRequestDispatcher("/WEB-INF/jsp/specificSearch.jsp").
                    forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Airport> originsDirect = new ArrayList<>();
        List<Airport> originsReturn = new ArrayList<>();
        List<Airport> destinationsDirect = new ArrayList<>();
        List<Airport> destinationsReturn = new ArrayList<>();

        if (req.getParameter("originDirectIsVNO") != null && req.getParameter("originDirectIsVNO").equals("Y")) {
            originsDirect.add(new Airport("VNO"));
        }
        if (req.getParameter("originDirectIsKUN") != null && req.getParameter("originDirectIsKUN").equals("Y")) {
            originsDirect.add(new Airport("KUN"));
        }
        if (req.getParameter("originDirectIsWMI") != null && req.getParameter("originDirectIsWMI").equals("Y")) {
            originsDirect.add(new Airport("WMI"));
        }
        if (req.getParameter("originDirectIsWAW") != null && req.getParameter("originDirectIsWAW").equals("Y")) {
            originsDirect.add(new Airport("WAW"));
        }

        if (req.getParameter("originReturnIsVNO") != null && req.getParameter("originReturnIsVNO").equals("Y")) {
            originsReturn.add(new Airport("VNO"));
        }
        if (req.getParameter("originReturnIsKUN") != null && req.getParameter("originReturnIsKUN").equals("Y")) {
            originsReturn.add(new Airport("KUN"));
        }
        if (req.getParameter("originReturnIsWMI") != null && req.getParameter("originReturnIsWMI").equals("Y")) {
            originsReturn.add(new Airport("WMI"));
        }
        if (req.getParameter("originReturnIsWAW") != null && req.getParameter("originReturnIsWAW").equals("Y")) {
            originsReturn.add(new Airport("WAW"));
        }

        destinationsDirect.add(new Airport(req.getParameter("airportDestinationDirect1")));
        if (req.getParameter("airportDestinationDirect2") != null
                && !req.getParameter("airportDestinationDirect1").equals(req.getParameter("airportDestinationDirect2")))
        {
            destinationsDirect.add(new Airport(req.getParameter("airportDestinationDirect2")));
        }
        if (req.getParameter("airportDestinationDirect3") != null
                && !req.getParameter("airportDestinationDirect1").equals(req.getParameter("airportDestinationDirect3"))
                && !req.getParameter("airportDestinationDirect2").equals(req.getParameter("airportDestinationDirect3"))
        )
        {
            destinationsDirect.add(new Airport(req.getParameter("airportDestinationDirect3")));
        }

        destinationsReturn.add(new Airport(req.getParameter("airportDestinationReturn1")));
        if (req.getParameter("airportDestinationReturn2") != null
                && !req.getParameter("airportDestinationReturn1").equals(req.getParameter("airportDestinationReturn2")))
        {
            destinationsReturn.add(new Airport(req.getParameter("airportDestinationReturn2")));
        }
        if (req.getParameter("airportDestinationReturn3") != null
                && !req.getParameter("airportDestinationReturn1").equals(req.getParameter("airportDestinationReturn3"))
                && !req.getParameter("airportDestinationReturn2").equals(req.getParameter("airportDestinationReturn3"))
        )
        {
            destinationsReturn.add(new Airport(req.getParameter("airportDestinationReturn3")));
        }

        String localDate = req.getParameter("startingDate");
        String regex = "-";
        String[] localDateArr = localDate.split(regex);
        LocalDate localDateL = LocalDate.of(Integer.parseInt(localDateArr[0]), Integer.parseInt(localDateArr[1]), Integer.parseInt(localDateArr[2]));
        Integer deep = Integer.parseInt(req.getParameter("dayCount"));
        Integer min = Integer.parseInt(req.getParameter("minDay"));
        Integer max = Integer.parseInt(req.getParameter("maxDay"));

        TripScannerImpl tripScanner = new TripScannerImpl(originsDirect, destinationsDirect, destinationsReturn, originsReturn, localDateL, deep, min, max);

        Boolean isStartingSameAirport = false;
        Boolean isStartingSameCity = false;
        Boolean isStartingSameCountry = false;

        Boolean isEndingSameAirport = false;
        Boolean isEndingSameCity = false;
        Boolean isEndingSameCountry = false;

        String endingFilter = req.getParameter("endingFilter");
        String startingFilter = req.getParameter("startingFilter");

        switch (endingFilter){
            case "airportFilter":{
                isEndingSameAirport=true;
                break;
            }
            case "cityFilter":{
                isEndingSameCity=true;
                break;
            }
            case "countryFilter":{
                isEndingSameCountry=true;
                break;
            }
        }

        switch (startingFilter){
            case "airportFilter":{
                isStartingSameAirport=true;
                break;
            }

            case "cityFilter":{
                isStartingSameCity=true;
                break;
            }

            case "countryFilter":{
                isStartingSameCountry=true;
                break;
            }

        }

        List<Trip> trips = tripScanner.searchRoundTrip(isStartingSameCountry,isStartingSameCity,isStartingSameAirport,isEndingSameCountry,isEndingSameCity,isEndingSameAirport);

        req.setAttribute("trips", trips);
        HttpSession httpSession = req.getSession();

        Object searchIdObj = httpSession.getAttribute("searchIdList");

        if (searchIdObj == null) {
            searchIdObj = new ArrayList<Long>();
        }
        List<Long> searchIdList = (ArrayList<Long>) searchIdObj;
        if (trips.size() > 0) {
            searchIdList.add(trips.get(0).getSearchId());
        }
        httpSession.setAttribute("searchIdList", searchIdList);

        req.getRequestDispatcher("/WEB-INF/jsp/resultSearch.jsp").
                forward(req, resp);

    }
}
