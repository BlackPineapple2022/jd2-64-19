package by.academy.it.travelcompany.scanner.tripscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.airport.AirportInfoCentre;
import by.academy.it.travelcompany.scanner.flightscanner.FlightScannerThread;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TripScannerImpl {
    private List <Airport> whereIWantToGetTo;
    private LocalDate startingDate;
    private Integer dayQuantityForSearch;

    public TripScannerImpl(List<Airport> whereIWantToGetTo, LocalDate startingDate, Integer dayQuantityForSearch) {
        this.whereIWantToGetTo = whereIWantToGetTo;
        this.startingDate = startingDate;
        this.dayQuantityForSearch = dayQuantityForSearch;
    }

    public void searchTrip(){
        List <Airport> origins = new ArrayList<>();
        origins.add(new Airport("VNO"));
        origins.add(new Airport("KUN"));
        origins.add(new Airport("WAW"));
        origins.add(new Airport("WMI"));

        Set <String> routeMap = AirportInfoCentre.getRouteMap(origins,whereIWantToGetTo);
        System.out.println(routeMap);
        for (String str:routeMap ) {
            String regex = "--";
            String [] routeArr = str.split(regex);
            FlightScannerThread fst = new FlightScannerThread(
                    Airline.valueOf(routeArr[0]),
                    new Airport(routeArr[1]),
                    new Airport(routeArr[2]),
                    startingDate,
                    dayQuantityForSearch);
            fst.start();
        }
    }

    public List<Airport> getWhereIWantToGetTo() {
        return whereIWantToGetTo;
    }

    public void setWhereIWantToGetTo(List<Airport> whereIWantToGetTo) {
        this.whereIWantToGetTo = whereIWantToGetTo;
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
}
