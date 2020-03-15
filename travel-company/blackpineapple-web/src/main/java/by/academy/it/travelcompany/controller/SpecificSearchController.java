package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.entity.Airport;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RoundTrip;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Controller
@Slf4j
public class SpecificSearchController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AirportService airportService;

    @Autowired
    RouteMapService routeMapService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/specific", method = RequestMethod.GET)
    public String specificSearch() {
        log.debug("specific controller");

        List<Airport> startedAirportList = airportService.getStartedAirportList();
        httpSession.setAttribute("allStartedAirports", startedAirportList);

        Integer defaultDay = LocalDate.now().getDayOfMonth();
        Integer defaultMonth = LocalDate.now().getMonthValue();
        Integer defaultYear = LocalDate.now().getYear();

        Integer defaultDayFinish = LocalDate.now().plusDays(15).getDayOfMonth();
        Integer defaultMonthFinish = LocalDate.now().plusDays(15).getMonthValue();
        Integer defaultYearFinish = LocalDate.now().plusDays(15).getYear();

        httpSession.setAttribute("defaultStartDate", "" + defaultMonth + "/" + defaultDay + "/" + defaultYear);
        httpSession.setAttribute("defaultFinishDate", "" + defaultMonthFinish + "/" + defaultDayFinish + "/" + defaultYearFinish);

        httpSession.setAttribute("checkedVNOStart", "checked");
        httpSession.setAttribute("checkedKUNStart", "checked");
        httpSession.setAttribute("checkedWMIStart", "checked");
        httpSession.setAttribute("checkedWAWStart", "checked");

        httpSession.setAttribute("checkedVNOFinish", "checked");
        httpSession.setAttribute("checkedKUNFinish", "checked");
        httpSession.setAttribute("checkedWMIFinish", "checked");
        httpSession.setAttribute("checkedWAWFinish", "checked");

        httpSession.setAttribute("checkedNoFilter", "checked");
        httpSession.setAttribute("checkedNoFilterR", "checked");

        httpSession.setAttribute("displayDiv2", "none");
        httpSession.setAttribute("displayDiv3", "none");
        httpSession.setAttribute("displayDiv4", "none");
        httpSession.setAttribute("displayDiv5", "none");

        httpSession.setAttribute("displayDivR2", "none");
        httpSession.setAttribute("displayDivR3", "none");
        httpSession.setAttribute("displayDivR4", "none");
        httpSession.setAttribute("displayDivR5", "none");

        httpSession.setAttribute("minDay", "10");
        httpSession.setAttribute("maxDay", "15");

        httpSession.setAttribute("schotD", "1");
        httpSession.setAttribute("schotR", "1");

        httpSession.removeAttribute("errorCodeSet");


        Airport airport = airportService.getByCode("BVA");
        httpSession.setAttribute("defaultAirport1", airport);
        httpSession.setAttribute("defaultAirportReturn1", airport);

        return "specific";
    }

    @RequestMapping(value = "/specific", method = RequestMethod.POST)
    public String specificSearchPost(

            @RequestParam(value = "originDirectIsVNO", required = false) String originDirectIsVNO,
            @RequestParam(value = "originDirectIsKUN", required = false) String originDirectIsKUN,
            @RequestParam(value = "originDirectIsWMI", required = false) String originDirectIsWMI,
            @RequestParam(value = "originDirectIsWAW", required = false) String originDirectIsWAW,

            @RequestParam(value = "originReturnIsVNO", required = false) String originReturnIsVNO,
            @RequestParam(value = "originReturnIsKUN", required = false) String originReturnIsKUN,
            @RequestParam(value = "originReturnIsWMI", required = false) String originReturnIsWMI,
            @RequestParam(value = "originReturnIsWAW", required = false) String originReturnIsWAW,

            @RequestParam("startingFilter") String startingFilter,
            @RequestParam("endingFilter") String endingFilter,

            @RequestParam("airportDestinationDirect1") String airportDestinationDirect1,
            @RequestParam("airportDestinationDirect2") String airportDestinationDirect2,
            @RequestParam("airportDestinationDirect3") String airportDestinationDirect3,
            @RequestParam("airportDestinationDirect4") String airportDestinationDirect4,
            @RequestParam("airportDestinationDirect5") String airportDestinationDirect5,

            @RequestParam("airportDestinationReturn1") String airportDestinationReturn1,
            @RequestParam("airportDestinationReturn2") String airportDestinationReturn2,
            @RequestParam("airportDestinationReturn3") String airportDestinationReturn3,
            @RequestParam("airportDestinationReturn4") String airportDestinationReturn4,
            @RequestParam("airportDestinationReturn5") String airportDestinationReturn5,

            @RequestParam("startingDate") String startingDate,
            @RequestParam("endingDate") String endingDate,

            @RequestParam("minDay") String minDay,
            @RequestParam("maxDay") String maxDay

    ) {
        ArrayList<String> attributeList = Collections.list(httpSession.getAttributeNames());

        for (String a : attributeList) {
            if (!a.equals("user")&&(!a.equals("allStartedAirports"))){
                httpSession.removeAttribute(a);
            }
        }

        if (originDirectIsVNO == null) {
            originDirectIsVNO = "";
        }if (originDirectIsKUN == null) {
            originDirectIsKUN = "";
        }if (originDirectIsWMI == null) {
            originDirectIsWMI = "";
        }if (originDirectIsWAW == null) {
            originDirectIsWAW = "";
        }if (originReturnIsVNO == null) {
            originReturnIsVNO = "";
        }if (originReturnIsKUN == null) {
            originReturnIsKUN = "";
        }if (originReturnIsWMI == null) {
            originReturnIsWMI = "";
        }if (originReturnIsWAW == null) {
            originReturnIsWAW = "";
        }

        Integer schotD = 1;
        Integer schotR = 1;

        Set<String> originAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportDirectCodeSet = new HashSet<>();
        Set<String> destinationAirportReturnCodeSet = new HashSet<>();
        Set<String> originAirportReturnCodeSet = new HashSet<>();

        List<Airport> airportDirectList = new ArrayList<>();
        List<Airport> airportReturnList = new ArrayList<>();

        Boolean hasError = false;
        Set<Integer> errorCodeSet = new HashSet<>();

        if (originDirectIsVNO.equals("")
                && originDirectIsKUN.equals("")
                && originDirectIsWMI.equals("")
                && originDirectIsWAW.equals("")
        ) {
            hasError = true;
            errorCodeSet.add(1);//Стартовые точки не выбраны
        }

        if (originReturnIsVNO.equals("")
                && originReturnIsKUN.equals("")
                && originReturnIsWMI.equals("")
                && originReturnIsWAW.equals("")
        ) {
            hasError = true;
            errorCodeSet.add(2);//Конечные точки не выбраны
        }

        if (originDirectIsVNO.equals("Y")) {
            originAirportDirectCodeSet.add("VNO");
            httpSession.setAttribute("checkedVNOStart", "checked");
        }
        if (originDirectIsKUN.equals("Y")) {
            originAirportDirectCodeSet.add("KUN");
            httpSession.setAttribute("checkedKUNStart", "checked");
        }
        if (originDirectIsWMI.equals("Y")) {
            originAirportDirectCodeSet.add("WMI");
            httpSession.setAttribute("checkedWMIStart", "checked");
        }
        if (originDirectIsWAW.equals("Y")) {
            originAirportDirectCodeSet.add("WAW");
            httpSession.setAttribute("checkedWAWStart", "checked");
        }


        if (originReturnIsVNO.equals("Y")) {
            originAirportReturnCodeSet.add("VNO");
            httpSession.setAttribute("checkedVNOFinish", "checked");
        }
        if (originReturnIsKUN.equals("Y")) {
            originAirportReturnCodeSet.add("KUN");
            httpSession.setAttribute("checkedKUNFinish", "checked");
        }
        if (originReturnIsWMI.equals("Y")) {
            originAirportReturnCodeSet.add("WMI");
            httpSession.setAttribute("checkedWMIFinish", "checked");
        }
        if (originReturnIsWAW.equals("Y")) {
            originAirportReturnCodeSet.add("WAW");
            httpSession.setAttribute("checkedWAWFinish", "checked");
        }

        Boolean isStartingSameAirport = false;
        Boolean isStartingSameCity = false;
        Boolean isStartingSameCountry = false;

        Boolean isEndingSameAirport = false;
        Boolean isEndingSameCity = false;
        Boolean isEndingSameCountry = false;

        switch (startingFilter) {
            case "airportFilter": {
                isStartingSameAirport = true;
                httpSession.setAttribute("checkedAirportFilter", "checked");
                break;
            }
            case "cityFilter": {
                isStartingSameCity = true;
                httpSession.setAttribute("checkedCityFilter", "checked");
                break;
            }
            case "countryFilter": {
                isStartingSameCountry = true;
                httpSession.setAttribute("checkedCountryFilter", "checked");
                break;
            }
            default: {
                httpSession.setAttribute("checkedNoFilter", "checked");
                break;
            }
        }

        switch (endingFilter) {
            case "airportFilter": {
                isEndingSameAirport = true;
                httpSession.setAttribute("checkedAirportFilterR", "checked");
                break;
            }
            case "cityFilter": {
                isEndingSameCity = true;
                httpSession.setAttribute("checkedCityFilterR", "checked");
                break;
            }
            case "countryFilter": {
                isEndingSameCountry = true;
                httpSession.setAttribute("checkedCountryFilterR", "checked");
                break;
            }
            default: {
                httpSession.setAttribute("checkedNoFilterR", "checked");
                break;
            }
        }

        if (!airportDestinationDirect1.equals("")
        ) {
            destinationAirportDirectCodeSet.add(airportDestinationDirect1);
            Airport airport = airportService.getByCode(airportDestinationDirect1);
            httpSession.setAttribute("defaultAirport1", airport);
        }

        if (!airportDestinationDirect2.equals("")
                && !airportDestinationDirect2.equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(airportDestinationDirect2);
            Airport airport = airportService.getByCode(airportDestinationDirect2);
            httpSession.setAttribute("defaultAirport2", airport);
            airportDirectList.add(airport);
        }


        if (!airportDestinationDirect3.equals("")
                && !airportDestinationDirect3.equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(airportDestinationDirect3);
            Airport airport = airportService.getByCode(airportDestinationDirect3);
            httpSession.setAttribute("defaultAirport3", airport);
            airportDirectList.add(airport);
        }

        if (!airportDestinationDirect4.equals("")
                && !airportDestinationDirect4.equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(airportDestinationDirect4);
            Airport airport = airportService.getByCode(airportDestinationDirect4);
            httpSession.setAttribute("defaultAirport4", airport);
            airportDirectList.add(airport);
        }

        if (!airportDestinationDirect5.equals("")
                && !airportDestinationDirect5.equals("NONE")
        ) {
            destinationAirportDirectCodeSet.add(airportDestinationDirect5);
            Airport airport = airportService.getByCode(airportDestinationDirect5);
            httpSession.setAttribute("defaultAirport5", airport);
            airportDirectList.add(airport);
        }

        if (!airportDestinationReturn1.equals("")
        ) {
            destinationAirportReturnCodeSet.add(airportDestinationReturn1);
            Airport airport = airportService.getByCode(airportDestinationReturn1);
            httpSession.setAttribute("defaultAirportReturn1", airport);
        }

        if (!airportDestinationReturn2.equals("")
                && !airportDestinationReturn2.equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(airportDestinationReturn2);
            Airport airport = airportService.getByCode(airportDestinationReturn2);
            httpSession.setAttribute("defaultAirportReturn2", airport);
            airportReturnList.add(airport);
        }

        if (!airportDestinationReturn3.equals("")
                && !airportDestinationReturn3.equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(airportDestinationReturn3);
            Airport airport = airportService.getByCode(airportDestinationReturn3);
            httpSession.setAttribute("defaultAirportReturn3", airport);
            airportReturnList.add(airport);
        }

        if (!airportDestinationReturn4.equals("")
                && !airportDestinationReturn4.equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(airportDestinationReturn4);
            Airport airport = airportService.getByCode(airportDestinationReturn4);
            httpSession.setAttribute("defaultAirportReturn4", airport);
            airportReturnList.add(airport);
        }

        if (!airportDestinationReturn5.equals("")
                && !airportDestinationReturn5.equals("NONE")
        ) {
            destinationAirportReturnCodeSet.add(airportDestinationReturn5);
            Airport airport = airportService.getByCode(airportDestinationReturn5);
            httpSession.setAttribute("defaultAirportReturn5", airport);
            airportReturnList.add(airport);
        }

        Set<RouteMap> routeMapSet = routeMapService.getRouteMapSetByAirportCodeSets(
                originAirportDirectCodeSet, destinationAirportDirectCodeSet, destinationAirportReturnCodeSet, originAirportReturnCodeSet);

        httpSession.setAttribute("defaultStartDate", startingDate);
        httpSession.setAttribute("defaultFinishDate", endingDate);

        String regex = "/";
        String[] localDateArrStart = startingDate.split(regex);
        String[] localDateArrEnd = endingDate.split(regex);

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
            min = Integer.parseInt(minDay);
        } catch (Exception e) {
            hasError = true;
            errorCodeSet.add(6);//Минимальное количество дней в путешествии не может быть меньше 2 и более 30
        }
        httpSession.setAttribute("minDay", min);
        if (min < 2 || min > 30) {
            hasError = true;
            errorCodeSet.add(6);//Минимальное количество дней в путешествии не может быть меньше 2 и более 30
        }

        Integer max = 0;
        try {
            max = Integer.parseInt(maxDay);
        } catch (Exception e) {
            hasError = true;
            errorCodeSet.add(7);//Максимальное количество дней в путешествии не может быть меньше 2 и более 30
        }
        httpSession.setAttribute("maxDay", max);
        if (max < 2 || max > 30) {
            hasError = true;
            errorCodeSet.add(7);//Максимальное количество дней в путешествии не может быть меньше 2 и более 30
        }

        if (max < min) {
            hasError = true;
            errorCodeSet.add(9);//Максимальное количество дней в путешествии не может быть меньше минимального
        }

        if (hasError) {
            httpSession.setAttribute("errorCodeSet", errorCodeSet);

            for (int i = 2; i < 6; i++) {
                String setAttributeValueDiv = "displayDiv" + (i);
                String setAttributeValueDivR = "displayDivR" + (i);
                httpSession.setAttribute(setAttributeValueDiv, "none");
                httpSession.setAttribute(setAttributeValueDivR, "none");
            }

            for (int i = 0; i < airportDirectList.size(); i++) {
                schotD++;
                String setAttributeValueAirport = "defaultAirport" + (2 + i);
                String setAttributeValueDiv = "displayDiv" + (2 + i);
                httpSession.setAttribute(setAttributeValueAirport, airportDirectList.get(i));
                httpSession.setAttribute(setAttributeValueDiv, "block");
            }

            for (int i = 0; i < airportReturnList.size(); i++) {
                schotR++;
                String setAttributeValueAirport = "defaultAirportReturn" + (2 + i);
                String setAttributeValueDiv = "displayDivR" + (2 + i);
                httpSession.setAttribute(setAttributeValueAirport, airportReturnList.get(i));
                httpSession.setAttribute(setAttributeValueDiv, "block");
            }

            httpSession.setAttribute("schotD", schotD);
            httpSession.setAttribute("schotR", schotR);

            return "specific";
        }

        //preparing data

        List<Flight> allDirectFlights = new ArrayList<>();
        List<Flight> allReturnFlights = new ArrayList<>();


        for (RouteMap r : routeMapSet) {
            if (r.getDirection().getName().equals("Direct")) {
                List<Flight> flights = flightService.getAllByRouteMapBetweenDateTime(r, localDateStartL, localDateEndL);
                allDirectFlights.addAll(flights);
            }

            if (r.getDirection().getName().equals("Return")) {
                List<Flight> flights = flightService.getAllByRouteMapBetweenDateTime(r, localDateStartL.plusDays(min-1), localDateEndL.plusDays(max-1));
                allReturnFlights.addAll(flights);
            }
        }

        Comparator<Flight> flightComparator = (o1, o2) -> {
            Double price1 = o1.getPrice() * currencyService.getEURMultiplier(o1.getCurrency());
            Double price2 = o2.getPrice() * currencyService.getEURMultiplier(o2.getCurrency());
            return price1.compareTo(price2);
        };

        allDirectFlights.sort(flightComparator);
        allReturnFlights.sort(flightComparator);

        List<RoundTrip> tripList = new ArrayList<>();

        for (Flight d : allDirectFlights) {
            LocalDate directDate = d.getDepartureDateTime().toLocalDate();
            for (Flight r : allReturnFlights) {
                LocalDate returnDate = r.getDepartureDateTime().toLocalDate();
                if (returnDate.isAfter(directDate.plusDays(min - 2)) && returnDate.isBefore(directDate.plusDays(max))) {
                    RoundTrip roundTrip = new RoundTrip();
                    roundTrip.setDirectFlight(d);
                    roundTrip.setReturnFlight(r);
                    Double directPriceEUR = d.getPrice()*currencyService.getEURMultiplier(d.getCurrency());
                    Double returnPriceEUR = r.getPrice()*currencyService.getEURMultiplier(r.getCurrency());
                    roundTrip.setPrice(directPriceEUR+returnPriceEUR);
                    tripList.add(roundTrip);
                }
            }
        }

        if (isEndingSameCountry) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportDestination().getCountry().equals(t.getReturnFlight().getRouteMap().getAirportOrigin().getCountry()));
        }

        if (isEndingSameCity) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportDestination().getCity().split("--")[0].equals(
                    t.getReturnFlight().getRouteMap().getAirportOrigin().getCity().split("--")[0]));
        }
        if (isEndingSameAirport) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportDestination().equals(t.getReturnFlight().getRouteMap().getAirportOrigin()));
        }
        if (isStartingSameCountry) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportOrigin().getCountry().equals(t.getReturnFlight().getRouteMap().getAirportDestination().getCountry()));
        }
        if (isStartingSameCity) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportOrigin().getCity().split("--")[0].equals(
                    t.getReturnFlight().getRouteMap().getAirportDestination().getCity().split("--")[0]));
        }
        if (isStartingSameAirport) {
            tripList.removeIf(t -> !t.getDirectFlight().getRouteMap().getAirportOrigin().equals(t.getReturnFlight().getRouteMap().getAirportDestination()));
        }

        tripList.sort(Comparator.comparing(RoundTrip::getPrice));



        List<RoundTrip> returnList = new ArrayList<>();

        for (int i = 0; i < tripList.size(); i++) {
            if (i < 20) {
                tripList.get(i).setId(Long.parseLong("" + i));
                returnList.add(tripList.get(i));
            }
        }

        if (returnList.size()==0){
            returnList=null;
        }

        httpSession.setAttribute("trips",returnList);
        System.err.println(returnList);
        return "specificresult";

    }


}
