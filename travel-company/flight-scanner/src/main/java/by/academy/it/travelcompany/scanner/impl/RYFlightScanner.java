package by.academy.it.travelcompany.scanner.impl;

import by.academy.it.travelcompany.scanner.FlightScanner;
import by.academy.it.travelcompany.FlightScannerData;
import by.academy.it.travelcompany.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
public class RYFlightScanner implements FlightScanner {

    private Set<Long> timeStampFlightFindSet = new TreeSet<>();

    private static final Object SYNC_RY = new Object();

    private Schedule schedule = new Schedule();

    private List<FlightScannerData> flightScannerDataList;

    public RYFlightScanner(FlightScannerData data) {
        this.flightScannerDataList = new ArrayList<>();
        this.flightScannerDataList.add(data);
    }

    public RYFlightScanner(List<FlightScannerData> dataList) {
        this.flightScannerDataList = dataList;
    }

    public RYFlightScanner(List<FlightScannerData> dataList, Schedule schedule) {
        this(dataList);
        this.schedule = schedule;
    }

    public RYFlightScanner(FlightScannerData data, Schedule schedule) {
        this(data);
        this.schedule = schedule;
    }

    public Set<Long> getTimeStampFlightFindSet() {
        return timeStampFlightFindSet;
    }

    @Override
    public List<JSONObject> parse(Long requestTimeout, Integer multiplierTimeout) {

        List<JSONObject> result = new ArrayList<>();

        for (FlightScannerData flightScannerData : flightScannerDataList) {

            LocalDate startingDate = flightScannerData.getStartingDate();
            Integer dayCount = flightScannerData.getDayCount();
            String routeMap = flightScannerData.getRouteMap();

            log.info("Start parsing on Ryanair.com, starting date: " + startingDate + " dayCount: " + dayCount + " RouteMap " + routeMap);

            final LocalDate finishLocalDate = startingDate.plusDays(dayCount);
            LocalDate currentLocalDate = LocalDate.of(startingDate.getYear(), startingDate.getMonthValue(), startingDate.getDayOfMonth());

            while (currentLocalDate.isBefore(finishLocalDate.plusDays(1))) {

                try {
                    String requestRY = getReqStringRY(currentLocalDate, routeMap);
                    JSONObject json = null;

                    synchronized (SYNC_RY) {
                        Thread.sleep((long) (requestTimeout * (1.0 + (multiplierTimeout * Math.random()))));
                        json = new JSONObject(readUrl(requestRY));
                    }

                    JSONArray jsonTrips = json.getJSONArray("trips");
                    String currencyStr = (String) json.get("currency");

                    JSONObject jsonTrip = (JSONObject) jsonTrips.get(0);
                    JSONArray jsonDates = jsonTrip.getJSONArray("dates");
                    JSONObject jsonDate = (JSONObject) jsonDates.get(0);
                    JSONArray jsonFlights = jsonDate.getJSONArray("flights");

                    for (int i = 0; i < jsonFlights.length(); i++) {
                        JSONObject jsonFlight = (JSONObject) jsonFlights.get(i);
                        JSONArray time = jsonFlight.getJSONArray("time");
                        String departureDateTime = (String) time.get(0);
                        String arriveDateTime = (String) time.get(1);
                        String flightNumber = (String) jsonFlight.get("flightNumber");
                        JSONObject jsonRegularFare = jsonFlight.getJSONObject("regularFare");
                        JSONArray jsonFares = jsonRegularFare.getJSONArray("fares");
                        JSONObject jsonFare = (JSONObject) jsonFares.get(0);
                        Double amount = (Double) (jsonFare.get("amount"));

                        Map<String,String> map = new HashMap();
                        map.put("routeMap",routeMap);
                        map.put("departureDateTime",departureDateTime);
                        map.put("arriveDateTime",arriveDateTime);
                        map.put("flightNumber",flightNumber);
                        map.put("currency",currencyStr);
                        map.put("amount",amount.toString());
                        JSONObject foundFlight = new JSONObject(map);

                        result.add(foundFlight);
                        log.info("Flight found: " + foundFlight);
                    }
                } catch (Exception e) {
                    log.error("Error getting flight " + flightScannerData, e);
                } finally {
                    timeStampFlightFindSet.add(System.currentTimeMillis());
                    currentLocalDate = schedule.getNextDate(currentLocalDate);
                }
            }
        }
        log.info("Flight scanning successfully ended");
        return result;
    }

    private String getReqStringRY(LocalDate dateOfSearch, String routeMap) {
        String originAirport = routeMap.split("--")[1];
        String destinationAirport = routeMap.split("--")[2];
        String dateOfSearchFormatted = dateOfSearch.getYear() + "-" + dateOfSearch.getMonthValue() + "-" + dateOfSearch.getDayOfMonth();
        String req = "https://www.ryanair.com/api/booking/v4/en-gb/availability?" +
                "ADT=1&TEEN=0&CHD=0&INF=0&" +
                "DateOut=" + dateOfSearchFormatted +
                "&DateIn=&Origin=" + originAirport +
                "&Destination=" + destinationAirport +
                "&isConnectedFlight=false&RoundTrip=false&Discount=0&tpAdults=1&tpTeens=0&tpChildren=0&tpInfants=0&" +
                "tpStartDate=" + dateOfSearchFormatted +
                "&tpEndDate=&tpOriginIata=" + originAirport +
                "&tpDestinationIata=" + destinationAirport +
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

    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    private LocalDateTime getLocalDateTimeFromString(String str, String regexDateFromTime, String regexDayMonthYear, String regexMinHour) {
        String date = str.split(regexDateFromTime)[0];
        String year = date.split(regexDayMonthYear)[0];
        String month = date.split(regexDayMonthYear)[1];
        String day = date.split(regexDayMonthYear)[2];
        String time = str.split(regexDateFromTime)[1];
        String hour = time.split(regexMinHour)[0];
        String min = time.split(regexMinHour)[1];
        return LocalDateTime.of(
                LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)),
                LocalTime.of(Integer.parseInt(hour), Integer.parseInt(min)));
    }

}