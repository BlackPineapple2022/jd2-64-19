package by.academy.it.travelcompany.scanner.tripscanner;

import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.local.TripServiceLocal;
import by.academy.it.travelcompany.service.local.TripServiceLocalImpl;
import by.academy.it.travelcompany.travelitem.airline.AirlineEnum;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.service.local.FlightServiceLocal;
import by.academy.it.travelcompany.service.local.FlightServiceLocalImpl;
import by.academy.it.travelcompany.travelitem.trip.Trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TripScannerImpl {

    private List<Airport> originAirportsDirect;
    private List<Airport> destinationAirportsDirect;

    private List<Airport> originAirportsReturn;
    private List<Airport> destinationAirportsReturn;

    private LocalDate startingDate;
    private Integer dayQuantity;
    private Integer minDayOfTrip;
    private Integer maxDayOfTrip;

    private List<FlightScannerImpl> threads = new ArrayList<>();
    private Boolean isSearchActive;

    private static final CurrencyScanner CURRENCY_SCANNER = CurrencyScannerImpl.getInstance();
    private static final TripServiceLocal TRIP_SERVICE = TripServiceLocalImpl.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightScannerImpl.class);

    private static final Integer DELAY_CHECK_ALL_TREADS_ARE_FINISHED = 1000;
    private static final Integer DELAY_FIRST_CHECK_ALL_TREADS_ARE_FINISHED = 10000;

    public TripScannerImpl(List<Airport> originAirportsDirect, List<Airport> destinationAirportsDirect, List<Airport> destinationAirportsReturn, List<Airport> originAirportsReturn, LocalDate startingDate, Integer dayQuantity, Integer minDayOfTrip, Integer maxDayOfTrip) {
        this.originAirportsDirect = originAirportsDirect;
        this.destinationAirportsDirect = destinationAirportsDirect;
        this.destinationAirportsReturn = destinationAirportsReturn;
        this.originAirportsReturn = originAirportsReturn;
        this.startingDate = startingDate;
        this.dayQuantity = dayQuantity;
        this.minDayOfTrip = minDayOfTrip;
        this.maxDayOfTrip = maxDayOfTrip;
    }

    public List<Trip> searchRoundTrip() {
        List<Trip> tripRes = new ArrayList<>();

        Long searchId = Long.parseLong("" + LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getDayOfMonth() + "" + LocalTime.now().getHour() + "" + LocalTime.now().getMinute() + "" + (int) (Math.random() * 100000));

        isSearchActive = true;
        Set<String> routeMap = AirportInfoCentre.getRouteMap(originAirportsDirect, destinationAirportsDirect, destinationAirportsReturn, originAirportsReturn);
        LOGGER.info("Starting search trip searchId: " + searchId);
        LOGGER.info("Route map generated: " + routeMap.toString());

        for (String str : routeMap) {
            String regex = "--";
            String[] routeArr = str.split(regex);

            if (routeArr[3].equals("Direct")) {
                FlightScannerImpl scanner = new FlightScannerImpl(
                        searchId,
                        AirlineEnum.valueOf(routeArr[0]),
                        new Airport(routeArr[1]),
                        new Airport(routeArr[2]),
                        startingDate,
                        dayQuantity,
                        "Direct");
                scanner.start();
                threads.add(scanner);
            }

            if (routeArr[3].equals("Return")) {
                FlightScannerImpl scanner = new FlightScannerImpl(
                        searchId,
                        AirlineEnum.valueOf(routeArr[0]),
                        new Airport(routeArr[1]),
                        new Airport(routeArr[2]),
                        startingDate.plusDays(minDayOfTrip - 1),
                        dayQuantity + maxDayOfTrip - minDayOfTrip,
                        "Return");
                scanner.start();
                threads.add(scanner);
            }
        }

        try {
            Thread.sleep(DELAY_FIRST_CHECK_ALL_TREADS_ARE_FINISHED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                Thread.sleep(DELAY_CHECK_ALL_TREADS_ARE_FINISHED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isSearchActive = false;

            for (FlightScannerImpl f : threads) {
                if (f.isAlive()) {
                    isSearchActive = true;
                    break;
                }
            }

            if (!isSearchActive) {

                FlightServiceLocal flightServiceLocal = FlightServiceLocalImpl.getInstance();
                List<Flight> directs = flightServiceLocal.getAllFlightBySearchIdAndDirection(searchId, "Direct");
                List<Flight> returns = flightServiceLocal.getAllFlightBySearchIdAndDirection(searchId, "Return");

                Comparator<Flight> flightComparator = (o1, o2) -> {
                    Double price1 = o1.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o1.getCurrency());
                    Double price2 = o2.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o2.getCurrency());
                    return price1.compareTo(price2);
                };

                directs.sort(flightComparator);
                returns.sort(flightComparator);

                for (Flight d : directs) {
                    LocalDate directDate = d.getDepartureTime().toLocalDate();
                    for (Flight r : returns) {
                        LocalDate returnDate = r.getDepartureTime().toLocalDate();
                        if (returnDate.isAfter(directDate.plusDays(minDayOfTrip - 2)) && returnDate.isBefore(directDate.plusDays(maxDayOfTrip))) {
                            List<Flight> flights = new ArrayList<>();
                            flights.add(d);
                            flights.add(r);
                            Double price = 0.0;
                            for (Flight f : flights) {
                                price += f.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(f.getCurrency());
                            }
                            Trip trip = new Trip(flights, price, searchId);
                            TRIP_SERVICE.addTrip(trip);
                        }

                    }
                }
                LOGGER.info("Searching trip is finished, searchId: " + searchId);
                tripRes = TRIP_SERVICE.getAllTripBySearchId(searchId);
                break;
            }
        }
        return tripRes;
    }

    public List<Trip> searchRoundTrip(Boolean isStartingSameCountry, Boolean isStartingSameCity, Boolean isStartingSameAirport, Boolean isEndingSameCountry, Boolean isEndingSameCity, Boolean isEndingSameAirport) {
        List<Trip> tripList = searchRoundTrip();
        if (isEndingSameCountry) {
            tripList.removeIf(t -> !t.getFlights().get(0).getDestinationAirport().getCountry().equals(t.getFlights().get(1).getOriginAirport().getCountry()));
        }
        if (isEndingSameCity) {
            for (Trip t : tripList) {
                String cityDestinationDirect = t.getFlights().get(0).getDestinationAirport().getCity().split("--")[0];
                String cityOriginReturn = t.getFlights().get(1).getOriginAirport().getCity().split("--")[0];
                if (!cityDestinationDirect.equals(cityOriginReturn)) {
                    tripList.remove(t);
                }
            }
        }
        if (isEndingSameAirport) {
            tripList.removeIf(t -> !t.getFlights().get(0).getDestinationAirport().equals(t.getFlights().get(1).getOriginAirport()));
        }
        if (isStartingSameCountry) {
            tripList.removeIf(t -> !t.getFlights().get(0).getOriginAirport().getCountry().equals(t.getFlights().get(1).getDestinationAirport().getCountry()));
        }
        if (isStartingSameCity) {
            for (Trip t : tripList) {
                String cityOriginDirect = t.getFlights().get(0).getOriginAirport().getCity().split("--")[0];
                String cityDestinationReturn = t.getFlights().get(1).getDestinationAirport().getCity().split("--")[0];
                if (!cityOriginDirect.equals(cityDestinationReturn)) {
                    tripList.remove(t);
                }
            }
        }
        if (isStartingSameAirport) {
            tripList.removeIf(t -> !t.getFlights().get(0).getOriginAirport().equals(t.getFlights().get(1).getDestinationAirport()));
        }
        return tripList;
    }


}
