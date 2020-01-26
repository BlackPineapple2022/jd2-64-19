package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.FlightDAO;
import by.academy.it.travelcompany.dao.RoundTripDAO;
import by.academy.it.travelcompany.dao.impl.FlightDAOImpl;
import by.academy.it.travelcompany.dao.impl.RoundTripDAOImpl;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScanner;
import by.academy.it.travelcompany.scanner.currencyscaner.CurrencyScannerImpl;
import by.academy.it.travelcompany.service.global.TripService;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.trip.Trip;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class TripServiceImpl implements TripService {

    private static final TripService INSTANCE = new TripServiceImpl();
    private static final CurrencyScanner CURRENCY_SCANNER = CurrencyScannerImpl.getInstance();

    private final RoundTripDAO roundTripDAO = RoundTripDAOImpl.getInstance();
    private final FlightDAO flightDAO = FlightDAOImpl.getInstance();

    private TripServiceImpl() {
    }

    public static TripService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Trip> getAllTrips() {
        return null;
    }

    @Override
    public Trip addTrip(Trip trip) {
        log.info("Adding trip to Base{}, trip:",trip);
        try {
            roundTripDAO.create(trip);
            return trip;
        }catch (SQLException e){
            log.error("Error while adding trip to Base", e);
        }
        return null;
    }

    @Override
    public void deleteTrip(Long id) {
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }

    @Override
    public List<Trip> getAllTripBySearchId(Long searchId) {
        List<Trip>  resultTrip = null;
        try {
            resultTrip = roundTripDAO.getAllBySearchId(searchId);
            for (Trip t: resultTrip) {
                Flight directFlight = t.getFlights().get(0);
                Flight returnFlight = t.getFlights().get(1);
                Long directFlightId = directFlight.getId();
                Long returnFlightId = returnFlight.getId();
                directFlight = flightDAO.read(directFlightId).get();
                returnFlight = flightDAO.read(returnFlightId).get();
                List <Flight> resultFlightList =new ArrayList<>();
                resultFlightList.add(directFlight);
                resultFlightList.add(returnFlight);
                t.setFlights(resultFlightList);
            }

        Collections.sort(resultTrip, new Comparator<Trip>() {
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
        }catch (SQLException e){
        }
        return resultTrip;
    }

    @Override
    public void deleteAllBySearchIdButFavourite(List<Long> searchIdList) {
        log.info ("User logout, delete all trip from base exclude favourite");
            try{
                roundTripDAO.deleteAllBySearchIdButFavourite(searchIdList);
            }catch (SQLException e){
                log.error("Error while delete all trip");
            }
    }
}

