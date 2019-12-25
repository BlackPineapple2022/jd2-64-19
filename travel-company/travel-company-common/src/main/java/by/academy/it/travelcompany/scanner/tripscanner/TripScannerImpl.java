package by.academy.it.travelcompany.scanner.tripscanner;

import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.airport.AirportInfoCentre;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerThread;
import by.academy.it.travelcompany.service.local.FlightService;
import by.academy.it.travelcompany.service.local.FlightServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TripScannerImpl {

    private List<Airport> originAirports;
    private List<Airport> destinationAirports;
    private LocalDate startingDate;
    private Integer dayQuantityForSearch;
    private Integer minDayOfTrip;
    private Integer maxDayOfTrip;

    private List<FlightScannerThread> threads = new ArrayList<>();
    private Boolean isSearchActive;

    private static final Integer DELAY_CHECK_ALL_TREADS_ARE_FINISHED = 1000;
    private static final Integer DELAY_FIRST_CHECK_ALL_TREADS_ARE_FINISHED = 10000;

    public TripScannerImpl(List<Airport> originAirports, List<Airport> destinationAirports, LocalDate startingDate, Integer dayQuantityForSearch, Integer minDayOfTrip, Integer maxDayOfTrip) {
        this.destinationAirports = destinationAirports;
        this.originAirports = originAirports;
        this.startingDate = startingDate;
        this.dayQuantityForSearch = dayQuantityForSearch;
        this.minDayOfTrip = minDayOfTrip;
        this.maxDayOfTrip = maxDayOfTrip;
    }

    public void searchTrip() {

        isSearchActive = true;
        Set<String> routeMap = AirportInfoCentre.getRouteMap(originAirports, destinationAirports);
        System.out.println(routeMap);

        for (String str : routeMap) {
            String regex = "--";
            String[] routeArr = str.split(regex);

            if (routeArr[3].equals("Direct")) {
                FlightScannerThread fst = new FlightScannerThread(
                        Airline.valueOf(routeArr[0]),
                        new Airport(routeArr[1]),
                        new Airport(routeArr[2]),
                        startingDate,
                        dayQuantityForSearch,
                        "Direct");
                fst.start();
                threads.add(fst);
            }

            if (routeArr[3].equals("Return")) {
                FlightScannerThread fst = new FlightScannerThread(
                        Airline.valueOf(routeArr[0]),
                        new Airport(routeArr[1]),
                        new Airport(routeArr[2]),
                        startingDate.plusDays(minDayOfTrip),
                        dayQuantityForSearch + maxDayOfTrip - minDayOfTrip,
                        "Return");
                fst.start();
                threads.add(fst);
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

            for (FlightScannerThread f : threads) {
                if (f.isAlive()) {
                    isSearchActive = true;
                    break;
                }
            }

            if (!isSearchActive) {

                FlightService flightService = FlightServiceImpl.getInstance();

                for (Flight f : flightService.getAllFlights()){
                    System.out.println(f);
                }
                break;
            }
        }
    }


}
