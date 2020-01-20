
package by.academy.it.travelcompany.scanner.tripscanner;

import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerImpl;
import by.academy.it.travelcompany.service.global.FlightService;
import by.academy.it.travelcompany.service.local.TripServiceLocal;
import by.academy.it.travelcompany.service.local.TripServiceLocalImpl;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.trip.Trip;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
public class TripScannerImpl {

    private Set<RouteMap> routeMapSet;
    private LocalDate startingDate;
    private Integer dayQuantity;
    private Integer minDayOfTrip;
    private Integer maxDayOfTrip;

    private List<FlightScannerImpl> threads = new ArrayList<>();
    private Boolean isSearchActive;

    private static final CurrencyScanner CURRENCY_SCANNER = CurrencyScannerImpl.getInstance();
    private static final TripServiceLocal TRIP_SERVICE = TripServiceLocalImpl.getInstance();

    private static final Integer DELAY_CHECK_ALL_TREADS_ARE_FINISHED = 1000;
    private static final Integer DELAY_FIRST_CHECK_ALL_TREADS_ARE_FINISHED = 10000;

    public TripScannerImpl(Set<RouteMap> routeMapSet, LocalDate startingDate, Integer dayQuantity, Integer minDayOfTrip, Integer maxDayOfTrip) {
        this.routeMapSet = routeMapSet;
        this.startingDate = startingDate;
        this.dayQuantity = dayQuantity;
        this.minDayOfTrip = minDayOfTrip;
        this.maxDayOfTrip = maxDayOfTrip;
    }

    public List<Trip> searchRoundTrip() {
        List<Trip> tripRes = new ArrayList<>();

        Long searchId = Long.parseLong("" + LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getDayOfMonth() + "" + LocalTime.now().getHour() + "" + LocalTime.now().getMinute() + "" + (int) (Math.random() * 100000));

        isSearchActive = true;
        log.info("Starting search trip searchId: " + searchId);

        for (RouteMap r : routeMapSet) {
            if (r.getDirection().getDirectionName().equals("Direct")) {
                FlightScannerImpl scanner = new FlightScannerImpl(searchId, r, startingDate, dayQuantity);
                scanner.start();
                threads.add(scanner);
            }

            if (r.getDirection().getDirectionName().equals("Return")) {
                FlightScannerImpl scanner = new FlightScannerImpl(searchId, r, startingDate.plusDays(minDayOfTrip - 1), dayQuantity + maxDayOfTrip - minDayOfTrip);
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
                log.error("Thread was interrupted");
            }

            isSearchActive = false;

            for (FlightScannerImpl f : threads) {
                if (f.isAlive()) {
                    isSearchActive = true;
                    break;
                }
            }

            if (!isSearchActive) {

                FlightService flightService = FlightServiceImpl.getInstance();
                List<Flight> directsAndReturns = flightService.getAllFlightBySearchId(searchId);

                List<Flight> directs = new ArrayList<>();
                List<Flight> returns = new ArrayList<>();

                for (Flight f : directsAndReturns) {
                    if (f.getRouteMap().getDirection().getDirectionName().equals("Direct")) {
                        directs.add(f);
                    }
                    if (f.getRouteMap().getDirection().getDirectionName().equals("Return")) {
                        returns.add(f);
                    }
                }

                Comparator<Flight> flightComparator = (o1, o2) -> {
                    Double price1 = o1.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o1.getCurrency().getCurrencyCode());
                    Double price2 = o2.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(o2.getCurrency().getCurrencyCode());
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
                                price += f.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(f.getCurrency().getCurrencyCode());
                            }
                            Trip trip = new Trip(flights, price, searchId);
                            TRIP_SERVICE.addTrip(trip);
                        }

                    }
                }
                log.info("Searching trip is finished, searchId: " + searchId);
                tripRes = TRIP_SERVICE.getAllTripBySearchId(searchId);
                break;
            }
        }
        return tripRes;
    }

    public List<Trip> searchRoundTrip(Boolean isStartingSameCountry, Boolean isStartingSameCity, Boolean isStartingSameAirport,
                                      Boolean isEndingSameCountry, Boolean isEndingSameCity, Boolean isEndingSameAirport) {
        List<Trip> tripList = searchRoundTrip();

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

        return tripList;
    }

}

