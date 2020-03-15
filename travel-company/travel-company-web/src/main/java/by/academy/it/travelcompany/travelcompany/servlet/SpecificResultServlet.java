package by.academy.it.travelcompany.travelcompany.servlet;

import by.academy.it.travelcompany.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.service.global.TripService;
import by.academy.it.travelcompany.service.global.imp.*;
import by.academy.it.travelcompany.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelcompany.travelitem.trip.Trip;
import by.academy.it.travelcompany.travelcompany.user.User;
import by.academy.it.travelcompany.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelcompany.user.favourite.Favourite;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        Integer schotD = 1;
        Integer schotR = 1;

        List <Airport> airportDirectList = new ArrayList<>();
        List <Airport> airportReturnList = new ArrayList<>();

        Long searchId = Long.parseLong("" + LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getDayOfMonth() + "" + LocalTime.now().getHour() + "" + LocalTime.now().getMinute() + "" + (int) (Math.random() * 100000));

        Set<String> originAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportReturnCodeSet = new HashSet<>();
        Set<String> originAirportReturnCodeSet = new HashSet<>();

        Boolean hasError = false;
        Set<Integer> errorCodeSet = new HashSet<>();

        if (req.getParameter("originDirectIsVNO") == null
                && req.getParameter("originDirectIsKUN") == null
                && req.getParameter("originDirectIsWMI") == null
                && req.getParameter("originDirectIsWAW") == null
        ) {
            hasError = true;
            errorCodeSet.add(1);//Стартовые точки не выбраны
        }

        if (req.getParameter("originReturnIsVNO") == null
                && req.getParameter("originReturnIsKUN") == null
                && req.getParameter("originReturnIsWMI") == null
                && req.getParameter("originReturnIsWAW") == null
        ) {
            hasError = true;
            errorCodeSet.add(2);//Конечные точки не выбраны
        }

        if (req.getParameter("originDirectIsVNO") != null && req.getParameter("originDirectIsVNO").equals("Y")) {
            originAirportDirectCodeSet.add("VNO");
            req.setAttribute("checkedVNOStart","checked");
        }
        if (req.getParameter("originDirectIsKUN") != null && req.getParameter("originDirectIsKUN").equals("Y")) {
            originAirportDirectCodeSet.add("KUN");
            req.setAttribute("checkedKUNStart","checked");
        }
        if (req.getParameter("originDirectIsWMI") != null && req.getParameter("originDirectIsWMI").equals("Y")) {
            originAirportDirectCodeSet.add("WMI");
            req.setAttribute("checkedWMIStart","checked");
        }
        if (req.getParameter("originDirectIsWAW") != null && req.getParameter("originDirectIsWAW").equals("Y")) {
            originAirportDirectCodeSet.add("WAW");
            req.setAttribute("checkedWAWStart","checked");
        }


        if (req.getParameter("originReturnIsVNO") != null && req.getParameter("originReturnIsVNO").equals("Y")) {
            originAirportReturnCodeSet.add("VNO");
            req.setAttribute("checkedVNOFinish","checked");
        }
        if (req.getParameter("originReturnIsKUN") != null && req.getParameter("originReturnIsKUN").equals("Y")) {
            originAirportReturnCodeSet.add("KUN");
            req.setAttribute("checkedKUNFinish","checked");
        }
        if (req.getParameter("originReturnIsWMI") != null && req.getParameter("originReturnIsWMI").equals("Y")) {
            originAirportReturnCodeSet.add("WMI");
            req.setAttribute("checkedWMIFinish","checked");
        }
        if (req.getParameter("originReturnIsWAW") != null && req.getParameter("originReturnIsWAW").equals("Y")) {
            originAirportReturnCodeSet.add("WAW");
            req.setAttribute("checkedWAWFinish","checked");
        }

        Boolean isStartingSameAirport = false;
        Boolean isStartingSameCity = false;
        Boolean isStartingSameCountry = false;

        Boolean isEndingSameAirport = false;
        Boolean isEndingSameCity = false;
        Boolean isEndingSameCountry = false;

        String endingFilter = req.getParameter("endingFilter");
        String startingFilter = req.getParameter("startingFilter");

        switch (startingFilter) {
            case "airportFilter": {
                isStartingSameAirport = true;
                req.setAttribute("checkedAirportFilter","checked");
                break;
            }
            case "cityFilter": {
                isStartingSameCity = true;
                req.setAttribute("checkedCityFilter","checked");
                break;
            }
            case "countryFilter": {
                isStartingSameCountry = true;
                req.setAttribute("checkedCountryFilter","checked");
                break;
            }
            default:{
                req.setAttribute("checkedNoFilter","checked");
                break;
            }
        }

        switch (endingFilter) {
            case "airportFilter": {
                isEndingSameAirport = true;
                req.setAttribute("checkedAirportFilterR","checked");
                break;
            }
            case "cityFilter": {
                isEndingSameCity = true;
                req.setAttribute("checkedCityFilterR","checked");

                break;
            }
            case "countryFilter": {
                isEndingSameCountry = true;
                req.setAttribute("checkedCountryFilterR","checked");
                break;
            }
            default:{
                req.setAttribute("checkedNoFilterR","checked");
                break;
            }
        }

        if (req.getParameter("airportDestinationDirect1") != null
                && !req.getParameter("airportDestinationDirect1").equals("")
        ) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect1"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationDirect1"));
            req.setAttribute("defaultAirport1",airport);
        }

        if (req.getParameter("airportDestinationDirect2") != null
                && !req.getParameter("airportDestinationDirect2").equals("")
                && !req.getParameter("airportDestinationDirect2").equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect2"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationDirect2"));
            airportDirectList.add(airport);
        }


        if (req.getParameter("airportDestinationDirect3") != null
                && !req.getParameter("airportDestinationDirect3").equals("")
                && !req.getParameter("airportDestinationDirect3").equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect3"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationDirect3"));
            airportDirectList.add(airport);
        }

        if (req.getParameter("airportDestinationDirect4") != null
                && !req.getParameter("airportDestinationDirect4").equals("")
                && !req.getParameter("airportDestinationDirect4").equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect4"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationDirect4"));
            airportDirectList.add(airport);
        }

        if (req.getParameter("airportDestinationDirect5") != null
                && !req.getParameter("airportDestinationDirect5").equals("")
                && !req.getParameter("airportDestinationDirect5").equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(req.getParameter("airportDestinationDirect5"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationDirect5"));
            airportDirectList.add(airport);
        }

        if (req.getParameter("airportDestinationReturn1") != null
                && !req.getParameter("airportDestinationReturn1").equals("")
        ) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn1"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationReturn1"));
            req.setAttribute("defaultAirportReturn1",airport);

        }

        if (req.getParameter("airportDestinationReturn2") != null
                && !req.getParameter("airportDestinationReturn2").equals("")
                && !req.getParameter("airportDestinationReturn2").equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn2"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationReturn2"));
            airportReturnList.add(airport);
        }

        if (req.getParameter("airportDestinationReturn3") != null
                && !req.getParameter("airportDestinationReturn3").equals("")
                && !req.getParameter("airportDestinationReturn3").equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn3"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationReturn3"));
            airportReturnList.add(airport);
        }

        if (req.getParameter("airportDestinationReturn4") != null
                && !req.getParameter("airportDestinationReturn4").equals("")
                && !req.getParameter("airportDestinationReturn4").equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn4"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationReturn4"));
            airportReturnList.add(airport);
        }

        if (req.getParameter("airportDestinationReturn5") != null
                && !req.getParameter("airportDestinationReturn5").equals("")
                && !req.getParameter("airportDestinationReturn5").equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(req.getParameter("airportDestinationReturn5"));
            Airport airport = AirportServiceImpl.getInstance().getAirportByCode(req.getParameter("airportDestinationReturn5"));
            airportReturnList.add(airport);
        }

        Set<RouteMap> routeMapSet = RouteMapServiceImpl.getInstance().getRouteMapSetByAirportCodeSets(
                originAirportDirectCodeSet, destinationAirportDirectCodeSet, destinationAirportReturnCodeSet, originAirportReturnCodeSet);

        String localDateStart = req.getParameter("startingDate");
        String localDateEnd = req.getParameter("endingDate");

        req.setAttribute("defaultStartDate",localDateStart);
        req.setAttribute("defaultFinishDate",localDateEnd);

        String regex = "/";
        String[] localDateArrStart = localDateStart.split(regex);
        String[] localDateArrEnd = localDateEnd.split(regex);

        LocalDate localDateStartL = null;
        LocalDate localDateEndL = null;

        try {
            localDateStartL = LocalDate.of(Integer.parseInt(localDateArrStart[2]), Integer.parseInt(localDateArrStart[0]), Integer.parseInt(localDateArrStart[1]));
        } catch (Exception e) {
            hasError = true;
            errorCodeSet.add(3);//Неверно указана начальная дата
        }

        try {
            localDateEndL = LocalDate.of(Integer.parseInt(localDateArrEnd[2]), Integer.parseInt(localDateArrEnd[0]), Integer.parseInt(localDateArrEnd[1]));
        } catch (Exception e) {
            hasError = true;
            errorCodeSet.add(4);//Неверно указана конечная дата
        }

        if (localDateEndL != null && localDateStartL != null) {
            if (localDateEndL.isBefore(localDateStartL)) {
                hasError = true;
                errorCodeSet.add(5);//Конечная дата не может быть раньше начальной
            }
        }

                Integer min = 0;
        try {
            min = Integer.parseInt(req.getParameter("minDay"));
        }catch (Exception e){
            hasError = true;
            errorCodeSet.add(6);//Минимальное количество дней в путешествии не может быть меньше 2 и более 30
        }
        req.setAttribute("minDay",min);
        if (min < 2 || min > 30) {
            hasError = true;
            errorCodeSet.add(6);//Минимальное количество дней в путешествии не может быть меньше 2 и более 30
        }

        Integer max = 0;
        try {
            max = Integer.parseInt(req.getParameter("maxDay"));
        }catch (Exception e){
            hasError = true;
            errorCodeSet.add(7);//Максимальное количество дней в путешествии не может быть меньше 2 и более 30
        }
        req.setAttribute("maxDay",max);
        if (max < 2 || max > 30) {
            hasError = true;
            errorCodeSet.add(7);//Максимальное количество дней в путешествии не может быть меньше 2 и более 30
        }

        if(max<min){
            hasError = true;
            errorCodeSet.add(9);//Максимальное количество дней в путешествии не может быть меньше минимального
        }



        if (hasError) {
            req.setAttribute("errorCodeSet", errorCodeSet);

            List<Airport> allStartedAirports = AirportServiceImpl.getInstance().getAll();
            allStartedAirports.removeIf(a -> a.getCode().equals("VNO") || a.getCode().equals("KUN") ||
                    a.getCode().equals("WAW") || a.getCode().equals("WMI"));
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                List<Favourite> favourites = FavouriteServiceImpl.getInstance().getAllFavouriteByUserId(user.getId());
                if (favourites != null) {
                    req.setAttribute("favourites", favourites);
                }
            }
            req.setAttribute("allStartedAirports", allStartedAirports);


            for (int i = 2; i<6; i++){
                String setAttributeValueDiv = "displayDiv" +(i);
                req.setAttribute(setAttributeValueDiv,"none");
            }


            for (int i = 2; i<6; i++){
                String setAttributeValueDiv = "displayDiv" +(i);
                String setAttributeValueDivR = "displayDivR" +(i);
                req.setAttribute(setAttributeValueDiv,"none");
                req.setAttribute(setAttributeValueDivR,"none");
            }

            for (int i = 0;i<airportDirectList.size();i++){
                schotD++;
                String setAttributeValueAirport = "defaultAirport" +(2+i);
                String setAttributeValueDiv = "displayDiv" +(2+i);
                req.setAttribute(setAttributeValueAirport,airportDirectList.get(i));
                req.setAttribute(setAttributeValueDiv,"block");
            }

            for (int i = 0;i<airportReturnList.size();i++){
                schotR++;
                String setAttributeValueAirport = "defaultAirportReturn" +(2+i);
                String setAttributeValueDiv = "displayDivR" +(2+i);
                req.setAttribute(setAttributeValueAirport,airportReturnList.get(i));
                req.setAttribute(setAttributeValueDiv,"block");
            }

            req.setAttribute("schotD",schotD);
            req.setAttribute("schotR",schotR);

            req.getRequestDispatcher("/WEB-INF/jsp/specific.jsp").forward(req, resp);
        } else {

            List<Flight> allDirectFlights = new ArrayList<>();
            List<Flight> allReturnFlights = new ArrayList<>();


            for (RouteMap r : routeMapSet) {
                if (r.getDirection().getDirectionName().equals("Direct")) {
                    List<Flight> flights = FlightServiceImpl.getInstance().getFlightListByRouteMapIdAndDates(r.getId(), localDateStartL, localDateEndL);
                    allDirectFlights.addAll(flights);
                }

                if (r.getDirection().getDirectionName().equals("Return")) {
                    List<Flight> flights = FlightServiceImpl.getInstance().getFlightListByRouteMapIdAndDates(r.getId(), localDateStartL.plusDays(min-1), localDateEndL.plusDays(max-1));
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
                    Trip trip = tripList.get(i);
                    trip.setId(Long.parseLong("" + i));
                    resultTripList.add(trip);
                }
            }

            if (resultTripList.size() == 0) {
                resultTripList = null;
            }

            req.setAttribute("trips", resultTripList);
            req.getRequestDispatcher("/WEB-INF/jsp/specificResultSearch.jsp").
                    forward(req, resp);


        }


    }
}