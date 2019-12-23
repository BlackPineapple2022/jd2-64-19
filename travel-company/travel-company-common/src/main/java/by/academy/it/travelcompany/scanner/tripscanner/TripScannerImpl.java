package by.academy.it.travelcompany.scanner.tripscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.AirportInfoCentre;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerThread;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

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

    private static final Integer DELAY = 1000;

    public TripScannerImpl(List<Airport> originAirports, List<Airport> destinationAirports, LocalDate startingDate, Integer dayQuantityForSearch, Integer minDayOfTrip, Integer maxDayOfTrip) {
        this.destinationAirports = destinationAirports;
        this.originAirports = originAirports;
        this.startingDate = startingDate;
        this.dayQuantityForSearch = dayQuantityForSearch;
        this.minDayOfTrip = minDayOfTrip;
        this.maxDayOfTrip = maxDayOfTrip;
    }

    public List<Airport> getDestinationAirports() {
        return destinationAirports;
    }

    public void setDestinationAirports(List<Airport> destinationAirports) {
        this.destinationAirports = destinationAirports;
    }

    public List<Airport> getOriginAirports() {
        return originAirports;
    }

    public void setOriginAirports(List<Airport> originAirports) {
        this.originAirports = originAirports;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public Integer getDayQuantityForSearch() {
        return dayQuantityForSearch;
    }

    public void setDayQuantityForSearch(Integer dayQuantityForSearch) {
        this.dayQuantityForSearch = dayQuantityForSearch;
    }

    public Integer getMinDayOfTrip() {
        return minDayOfTrip;
    }

    public void setMinDayOfTrip(Integer minDayOfTrip) {
        this.minDayOfTrip = minDayOfTrip;
    }

    public Integer getMaxDayOfTrip() {
        return maxDayOfTrip;
    }

    public void setMaxDayOfTrip(Integer maxDayOfTrip) {
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
            Thread.sleep(DELAY * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(DELAY);
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
            if (!isSearchActive){
                System.out.println("Well done");
                FlightService flightService = FlightServiceImpl.getInstance();
                System.out.println(flightService.getAllFlights());


                for (Flight f:flightService.getAllFlights()
                     ) {
                    //Найти директ с самой маленькой ценой.
                    // Проверить, есть ли в диапазоне от мин до максимальной даты путешествия обратные перелёты
                    // Если есть, то найти с минимальной ценой.
                    // Положить в триплист
                    // Повторить для директ трипа с следующей ценой.
                    // Что если одинаковы цены...

                }

                break;
            }

        }
    }


}
