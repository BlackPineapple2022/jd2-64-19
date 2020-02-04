package by.academy.it.travelcompany.servlet;

import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.service.global.TripService;
import by.academy.it.travelcompany.service.global.imp.FavouriteServiceImpl;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.service.global.imp.RouteMapServiceImpl;
import by.academy.it.travelcompany.service.global.imp.TripServiceImpl;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.user.User;
import by.academy.it.travelcompany.user.favourite.Favourite;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@WebServlet(urlPatterns = "/specificResult")
public class SpecificResultServlet extends HttpServlet {

    private static final CurrencyScanner CURRENCY_SCANNER = CurrencyScannerImpl.getInstance();

    private static final TripService TRIP_SERVICE = TripServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long searchId = Long.parseLong("" + LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getDayOfMonth() + "" + LocalTime.now().getHour() + "" + LocalTime.now().getMinute() + "" + (int) (Math.random() * 100000));

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
        String regex = "/";
        String[] localDateArrStart = localDateStart.split(regex);
        String[] localDateArrEnd = localDateEnd.split(regex);

        LocalDate localDateStartL = LocalDate.of(Integer.parseInt(localDateArrStart[2]), Integer.parseInt(localDateArrStart[0]), Integer.parseInt(localDateArrStart[1]));
        LocalDate localDateEndL = LocalDate.of(Integer.parseInt(localDateArrEnd[2]), Integer.parseInt(localDateArrEnd[0]), Integer.parseInt(localDateArrEnd[1]));

        Integer min = Integer.parseInt(req.getParameter("minDay"));
        Integer max = Integer.parseInt(req.getParameter("maxDay"));

        List<Flight> allDirectFlights = new ArrayList<>();
        List<Flight> allReturnFlights = new ArrayList<>();

        for (RouteMap r : routeMapSet) {
            if (r.getDirection().getDirectionName().equals("Direct")) {
                List<Flight> flights = FlightServiceImpl.getInstance().getFlightListByRouteMapIdAndDates(r.getId(), localDateStartL, localDateEndL);
                allDirectFlights.addAll(flights);
            }

            if (r.getDirection().getDirectionName().equals("Return")) {
                List<Flight> flights = FlightServiceImpl.getInstance().getFlightListByRouteMapIdAndDates(r.getId(), localDateStartL.plusDays(min), localDateEndL.plusDays(max));
                allReturnFlights.addAll(flights);
            }
        }

        Comparator<Flight> flightComparator = (o1, o2) -> {
            Double price1 = o1.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o1.getCurrency().getCurrencyCode());
            Double price2 = o2.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o2.getCurrency().getCurrencyCode());
            return price1.compareTo(price2);
        };

        allDirectFlights.sort(flightComparator);
        allReturnFlights.sort(flightComparator);

        List<Trip> tripList = new ArrayList<>();

        for (Flight d : allDirectFlights) {
            LocalDate directDate = d.getDepartureTime().toLocalDate();
            for (Flight r : allReturnFlights) {
                LocalDate returnDate = r.getDepartureTime().toLocalDate();
                if (returnDate.isAfter(directDate.plusDays(min - 2)) && returnDate.isBefore(directDate.plusDays(max))) {
                    List<Flight> flights = new ArrayList<>();
                    flights.add(d);
                    flights.add(r);
                    Double price = 0.0;
                    for (Flight f : flights) {
                        price += f.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(f.getCurrency().getCurrencyCode());
                    }
                    Trip trip = new Trip(flights, price, searchId);
                    tripList.add(trip);
                }
            }
        }

        log.info("Searching trip is finished, searchId: " + searchId);

        System.out.println("1" + " " + tripList);

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

        if (isEndingSameCountry) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getDestinationAirport().getCountry().equals(t.getFlights().get(1).getRouteMap().getOriginAirport().getCountry()));
        }

        if (isEndingSameCity) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getDestinationAirport().getCity().split("--")[0].equals(
                    t.getFlights().get(1).getRouteMap().getOriginAirport().getCity().split("--")[0]));
        }
        if (isEndingSameAirport) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getDestinationAirport().equals(t.getFlights().get(1).getRouteMap().getOriginAirport()));
        }
        if (isStartingSameCountry) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getOriginAirport().getCountry().equals(t.getFlights().get(1).getRouteMap().getDestinationAirport().getCountry()));
        }
        if (isStartingSameCity) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getOriginAirport().getCity().split("--")[0].equals(
                    t.getFlights().get(1).getRouteMap().getDestinationAirport().getCity().split("--")[0]));
        }
        if (isStartingSameAirport) {
            tripList.removeIf(t -> !t.getFlights().get(0).getRouteMap().getOriginAirport().equals(t.getFlights().get(1).getRouteMap().getDestinationAirport()));
        }

        List<Trip> resultTripList = new ArrayList<>();



        Collections.sort(tripList, new Comparator<Trip>() {
            @Override
            public int compare(Trip o1, Trip o2) {
                List<Flight> flightso1 = o1.getFlights();
                List<Flight> flightso2 = o2.getFlights();
                Double priceo1 = 0.0;
                for (Flight flight : flightso1) {
                    priceo1 += flight.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(flight.getCurrency().getCurrencyCode());
                }

                Double priceo2 = 0.0;
                for (Flight flight : flightso2) {
                    priceo2 += flight.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(flight.getCurrency().getCurrencyCode());
                }

                return priceo1.compareTo(priceo2);
            }
        });

        for (int i = 0; i < tripList.size(); i++) {
            if (i < 20) {
                resultTripList.add(tripList.get(i));
            }
        }

        req.setAttribute("trips", resultTripList);
        req.getRequestDispatcher("/WEB-INF/jsp/specificResultSearch.jsp").
                forward(req, resp);

    }
}