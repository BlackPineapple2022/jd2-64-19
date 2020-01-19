/*
package by.academy.it.travelcompany.service.local;

import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.trip.Trip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TripServiceLocalImpl implements TripServiceLocal {

    private static final TripServiceLocal INSTANCE = new TripServiceLocalImpl();
    private final List<Trip> trips = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);
    private static final CurrencyScanner CURRENCY_SCANNER = CurrencyScannerImpl.getInstance();

    private TripServiceLocalImpl() {
    }

    public static TripServiceLocal getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Trip> getAllTrips() {
        return trips;
    }

    @Override
    public Trip addTrip(Trip trip) {
        trip.setId(sequence.incrementAndGet());
        trips.add(trip);
        return trip;
    }

    @Override
    public void deleteTrip(Long id) {
        trips.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public Trip updateTrip(Trip trip) {
        deleteTrip(trip.getId());
        trips.add(trip);
        return trip;
    }

    @Override
    public List<Trip> getAllTripBySearchId(Long searchId) {


        List<Trip> allTrip = getAllTrips();
        List<Trip> resultTrip = new ArrayList<>();

        for (Trip t:allTrip) {
            if (t.getSearchId()!=null&&t.getSearchId().equals(searchId)){
                resultTrip.add(t);
            }
        }

        Collections.sort(resultTrip, new Comparator<Trip>() {
            @Override
            public int compare(Trip o1, Trip o2) {
                List<Flight> flightso1 = o1.getFlights();
                List<Flight> flightso2 = o2.getFlights();
                Double priceo1 = 0.0;
                for (Flight flight : flightso1) {
                    priceo1 += flight.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(flight.getCurrency());
                }

                Double priceo2 = 0.0;
                for (Flight flight : flightso2) {
                    priceo2 += flight.getTicketPrice() * CURRENCY_SCANNER.getEURMultiplier(flight.getCurrency());
                }

                return priceo1.compareTo(priceo2);
            }
        });
        return resultTrip;
    }
}
*/
