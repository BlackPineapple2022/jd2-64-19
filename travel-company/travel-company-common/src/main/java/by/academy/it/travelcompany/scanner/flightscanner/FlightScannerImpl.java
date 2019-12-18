package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightScannerImpl implements FlightScanner {

    private static final FlightScanner INSTANCE = new FlightScannerImpl();

    private static final FlightService flightService = FlightServiceImpl.getInstance();

    private FlightScannerImpl() {
    }

    public static FlightScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public void parseFlights(Airline airline,LocalDate startLocalDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination) {
        if (airline.equals(Airline.RY)) {
            for (int j = 0; j < dayQuantityForSearchFromToday; j++) {
                try {
                    String req = getReqStringRY(startLocalDate.plusDays(j), origin, destination);

                    JSONObject json = new JSONObject(readUrl(req));
                    JSONArray jsonTrips = json.getJSONArray("trips");
                    String currency = (String)json.get("currency");
                    JSONObject jsonTrip = (JSONObject) jsonTrips.get(0);
                    JSONArray jsonDates = jsonTrip.getJSONArray("dates");
                    JSONObject jsonDate = (JSONObject) jsonDates.get(0);
                    JSONArray jsonFlights = jsonDate.getJSONArray("flights");
                    for (int i = 0; i < jsonFlights.length(); i++) {
                        JSONObject jsonFlight = (JSONObject) jsonFlights.get(i);
                        JSONArray time = jsonFlight.getJSONArray("time");
                        String arriveDateTime = (String) time.get(0);
                        String departureDateTime = (String) time.get(1);
                        String flightNumber = (String) jsonFlight.get("flightNumber");
                        JSONObject jsonRegularFare = jsonFlight.getJSONObject("regularFare");
                        JSONArray jsonFares = jsonRegularFare.getJSONArray("fares");
                        JSONObject jsonFare = (JSONObject) jsonFares.get(0);
                        Double amount = (Double) (jsonFare.get("amount"));

                        String regexDateFromTime = "T";
                        String regexDate = "-";
                        String regexTime = ":";

                        String arriveDate = arriveDateTime.split(regexDateFromTime)[0];
                        String arriveYear = arriveDate.split(regexDate)[0];
                        String arriveMonth = arriveDate.split(regexDate)[1];
                        String arriveDay = arriveDate.split(regexDate)[2];
                        LocalDate arriveDateL = LocalDate.of(Integer.parseInt(arriveYear), Integer.parseInt(arriveMonth), Integer.parseInt(arriveDay));
                        String arriveTime = arriveDateTime.split(regexDateFromTime)[1];
                        String arriveHour = arriveTime.split(regexTime)[0];
                        String arriveMin = arriveTime.split(regexTime)[1];
                        LocalTime arriveTimeL = LocalTime.of(Integer.parseInt(arriveHour), Integer.parseInt(arriveMin));
                        LocalDateTime arriveLocalDateTime = LocalDateTime.of(arriveDateL, arriveTimeL);

                        String departureDate = departureDateTime.split(regexDateFromTime)[0];
                        String departureYear = departureDate.split(regexDate)[0];
                        String departureMonth = departureDate.split(regexDate)[1];
                        String departureDay = departureDate.split(regexDate)[2];
                        LocalDate departureDateL = LocalDate.of(Integer.parseInt(departureYear), Integer.parseInt(departureMonth), Integer.parseInt(departureDay));
                        String departureTime = departureDateTime.split(regexDateFromTime)[1];
                        String departureHour = departureTime.split(regexTime)[0];
                        String departureMin = departureTime.split(regexTime)[1];
                        LocalTime departureTimeL = LocalTime.of(Integer.parseInt(departureHour), Integer.parseInt(departureMin));
                        LocalDateTime departureLocalDateTime = LocalDateTime.of(departureDateL, departureTimeL);

                        flightService.updateOrCreateByLocalDateTime(new Flight(null, origin, destination, arriveLocalDateTime, departureLocalDateTime, Airline.RY,currency, amount, flightNumber));
                    }
                    Thread.sleep(20000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private String getReqStringRY(LocalDate dateOfSearch, Airport origin, Airport destination) {
        String dateOfSearchFormatted = dateOfSearch.getYear() + "-" + dateOfSearch.getMonthValue() + "-" + dateOfSearch.getDayOfMonth();
        String req = "https://www.ryanair.com/api/booking/v4/en-gb/availability?" +
                "ADT=1&TEEN=0&CHD=0&INF=0&" +
                "DateOut=" + dateOfSearchFormatted +
                "&DateIn=&Origin=" + origin.getCode() +
                "&Destination=" + destination.getCode() +
                "&isConnectedFlight=false&RoundTrip=false&Discount=0&tpAdults=1&tpTeens=0&tpChildren=0&tpInfants=0&" +
                "tpStartDate=" + dateOfSearchFormatted +
                "&tpEndDate=&tpOriginIata=" + origin.getCode() +
                "&tpDestinationIata=" + destination.getCode() +
                "&ToUs=AGREED" +
                "&tpIsConnectedFlight=false&tpIsReturn=false&tpDiscount=0";
        return req;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
