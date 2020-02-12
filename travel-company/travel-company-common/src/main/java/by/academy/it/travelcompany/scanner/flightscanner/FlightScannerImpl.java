package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.service.global.ScheduleService;
import by.academy.it.travelcompany.service.global.imp.CurrencyServiceImpl;
import by.academy.it.travelcompany.service.global.imp.ScheduleServiceImpl;
import by.academy.it.travelcompany.travelitem.airline.Airline;
import by.academy.it.travelcompany.travelitem.currency.Currency;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.travelitem.routemap.RouteMap;
import by.academy.it.travelcompany.travelitem.schedule.Schedule;
import by.academy.it.travelcompany.service.global.FlightService;
import by.academy.it.travelcompany.service.global.imp.FlightServiceImpl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.*;

@NoArgsConstructor
@Data
@Slf4j
public class FlightScannerImpl extends Thread {

    private static final int DELAY_REQ_RY = 1000;
    private static final int DELAY_REQ_RY_SYNC = 10000;
    private static final int DELAY_REQ_WIZZ = 100;

    private static final FlightService FLIGHT_SERVICE = FlightServiceImpl.getInstance();
    private static final ScheduleService SCHEDULE_SERVICE = ScheduleServiceImpl.getInstance();

    private static final Object SYNC_RY = new Object();
    private static final Object SYNC_WIZZ = new Object();

    private Long searchId;
    private RouteMap routeMap;
    private LocalDate startingDate;
    private Integer dayQuantity;

    public FlightScannerImpl(Long searchId, RouteMap routeMap, LocalDate startingDate, Integer dayQuantity) {
        this.searchId = searchId;
        this.routeMap = routeMap;
        this.startingDate = startingDate;
        this.dayQuantity = dayQuantity;
    }

    public List<Flight> parseFlightsRY() {
        log.info("Start parsing on Ryanair.com, Starting date:" + startingDate + " dayQuantity: " + dayQuantity
                + " originAirport: " + routeMap.getOriginAirport().getCode() + " destinationAirport: " + routeMap.getDestinationAirport().getCode() + " direction: " + routeMap.getDirection() + " searchId: " + searchId);
        final LocalDate finishLocalDate = startingDate.plusDays(dayQuantity);

        Schedule schedule = SCHEDULE_SERVICE.getSchedule(routeMap);

        LocalDate currentLocalDate = LocalDate.of(startingDate.getYear(), startingDate.getMonthValue(), startingDate.getDayOfMonth());

        List<Flight> result = new ArrayList<>();

        while (currentLocalDate.isBefore(finishLocalDate.plusDays(1))) {
            try {
                Thread.sleep(DELAY_REQ_RY);
                String req = getReqStringRY(currentLocalDate);
                JSONObject json = null;
                synchronized (SYNC_RY) {
                    json = new JSONObject(readUrl(req));
                    Thread.sleep(DELAY_REQ_RY_SYNC);
                }
                JSONArray jsonTrips = json.getJSONArray("trips");
                String currencyStr = (String) json.get("currency");
                Currency currency = new Currency(CurrencyServiceImpl.getInstance().getIdByName(currencyStr), currencyStr);
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

                    LocalDateTime arriveLocalDateTime = getLocalDateTimeFromString(arriveDateTime, "T", "-", ":");
                    LocalDateTime departureLocalDateTime = getLocalDateTimeFromString(departureDateTime, "T", "-", ":");

                    Flight f = new Flight(null, routeMap, departureLocalDateTime, arriveLocalDateTime, currency, amount, flightNumber);
                    f.setSearchId(searchId);
                    result.add(f);
                    log.info("Flight found: " + f);
                }
            } catch (Exception e) {
                log.error("Error getting flight, this date RY doesn't fly by this route: "+currentLocalDate, e);
            } finally {
                currentLocalDate = schedule.getNextDate(currentLocalDate);
            }
        }
        log.info("Flight scanning successfully ended");
        log.info("Finding flights going to Base{}");
        FLIGHT_SERVICE.updateByDateAndFlightNumberOrCreate(result);
        log.info("Going to Base{} finding flights successfully ended");
        return result;
    }

    public List<Flight> parseFlightsWIZZ() {
        log.info("Start parsing on Wizzair.com, Starting date:" + startingDate + " dayQuantity: " + dayQuantity
                + " originAirport: " + routeMap.getOriginAirport().getCode() + " destinationAirport: " + routeMap.getDestinationAirport().getCode() + " direction: " + routeMap.getDirection() + " searchId: " + searchId);

        final LocalDate finishLocalDate = startingDate.plusDays(dayQuantity);
        Schedule schedule = SCHEDULE_SERVICE.getSchedule(routeMap);
        List<Flight> result = new ArrayList<>();
        Map<String, List<String>> authMap = getWizzAirCookiesAndTokens();

        LocalDate currentLocalDate = LocalDate.of(startingDate.getYear(), startingDate.getMonthValue(), startingDate.getDayOfMonth());
        while (currentLocalDate.isBefore(finishLocalDate.plusDays(1))) {

            try {
                Thread.sleep(DELAY_REQ_WIZZ);
            } catch (InterruptedException e) {
            }

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://be.wizzair.com/10.10.0/Api/search/search");

            httpPost.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpPost.setHeader("accept-encoding", "gzip, deflate, br");
            httpPost.setHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
            httpPost.setHeader("cache-control", "no-cache");
            httpPost.setHeader("pragma", "no-cache");
            httpPost.setHeader("sec-fetch-mode", "navigate");
            httpPost.setHeader("sec-fetch-site", "none");
            httpPost.setHeader("sec-fetch-user", "?1");
            httpPost.setHeader("upgrade-insecure-requests", "1");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            httpPost.setHeader("x-requestverificationtoken", authMap.get("x-requestverificationtoken").get(0));

            for (int j = 0; j < authMap.get("Set-Cookie").size(); j++) {
                httpPost.addHeader("cookie", authMap.get("Set-Cookie").get(j));
            }

            HttpEntity httpEntity;

            String stringEntity = "{\"isFlightChange\":false,\"isSeniorOrStudent\":false,\"flightList\":[{\"departureStation\":\"" +
                    routeMap.getOriginAirport().getCode() +
                    "\",\"arrivalStation\":\"" +
                    routeMap.getDestinationAirport().getCode() +
                    "\",\"departureDate\":\"" +
                    getDateStringWIZZ(currentLocalDate) +
                    "\"}],\"adultCount\":1,\"childCount\":0,\"infantCount\":0,\"wdc\":true}";

            try {
                httpEntity = new StringEntity(stringEntity);
                httpPost.setEntity(httpEntity);
            } catch (UnsupportedEncodingException e) {
            }

            try (
                    CloseableHttpResponse response = httpClient.execute(httpPost)
            ) {

                HttpEntity responseEntity = response.getEntity();
                String responseBodyString = convertInputStreamToString(responseEntity.getContent());

                JSONObject json = new JSONObject(responseBodyString);

                JSONArray jsonOutBoundFlights = json.getJSONArray("outboundFlights");

                for (int l = 0; l < jsonOutBoundFlights.length(); l++) {
                    JSONObject jsonOutBoundFlight = (JSONObject) jsonOutBoundFlights.get(l);
                    String flightNumber = jsonOutBoundFlight.getString("carrierCode") + " " + jsonOutBoundFlight.getString("flightNumber");

                    String arriveDateTime = jsonOutBoundFlight.getString("arrivalDateTime");
                    String departureDateTime = jsonOutBoundFlight.getString("departureDateTime");

                    JSONArray jsonFares = jsonOutBoundFlight.getJSONArray("fares");
                    JSONObject jsonFare = (JSONObject) jsonFares.get(3);

                    JSONObject jsonBasePrice = jsonFare.getJSONObject("basePrice");
                    Double amount = (Double) jsonBasePrice.get("amount");
                    String currencyCode = (String) jsonBasePrice.get("currencyCode");
                    Currency currency = new Currency(CurrencyServiceImpl.getInstance().getIdByName(currencyCode), currencyCode);

                    LocalDateTime departureLocalDateTime = getLocalDateTimeFromString(departureDateTime, "T", "-", ":");
                    LocalDateTime arriveLocalDateTime = getLocalDateTimeFromString(arriveDateTime, "T", "-", ":");

                    Flight f = new Flight(null, routeMap, departureLocalDateTime, arriveLocalDateTime, currency, amount, flightNumber);
                    f.setSearchId(searchId);
                    result.add(f);
                    log.info("Flight found: " + f);
                }

            } catch (Exception e) {
                log.error("Error getting flight, this date WIZZ doesn't fly by this route: "+currentLocalDate, e);
            } finally {
                currentLocalDate = schedule.getNextDate(currentLocalDate);
            }
        }
        log.info("Finding flights going to Base{}");
        FLIGHT_SERVICE.updateByDateAndFlightNumberOrCreate(result);
        log.info("Going to Base{} finding flights successfully ended");
        return result;
    }

    @Override
    public void run() {
        if (routeMap.getAirline().equals(new Airline(null, "RY"))) {
            parseFlightsRY();
        }
        if (routeMap.getAirline().equals(new Airline(null, "WIZZ"))) {
            parseFlightsWIZZ();
        }
    }

    private Map<String, List<String>> getWizzAirCookiesAndTokens() {
        Map<String, List<String>> result = new HashMap<>();
        List<String> listOfValue = new ArrayList<>();
        List<String> keyList = new ArrayList<>();

        result.put("Set-Cookie", listOfValue);
        result.put("x-requestverificationtoken", keyList);

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest httpPost = new HttpPost("https://be.wizzair.com/10.10.0/Api/search/search");

        httpPost.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpPost.setHeader("accept-encoding", "gzip, deflate, br");
        httpPost.setHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.setHeader("cache-control", "no-cache");
        httpPost.setHeader("pragma", "no-cache");
        httpPost.setHeader("sec-fetch-mode", "navigate");
        httpPost.setHeader("sec-fetch-site", "none");
        httpPost.setHeader("sec-fetch-user", "?1");
        httpPost.setHeader("upgrade-insecure-requests", "1");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");

        try (
                CloseableHttpResponse response1 = httpClient.execute(httpPost)
        ) {

            Header[] headersResp = response1.getAllHeaders();
            String requestVerificationToken = "";
            for (Header h : headersResp) {
                if (h.getName().equals("Set-Cookie")) {
                    listOfValue.add(h.getValue());
                    if (h.getValue().startsWith("RequestVerificationToken")) {
                        for (int k = 25; k < 57; k++) {
                            requestVerificationToken = requestVerificationToken + h.getValue().charAt(k);
                        }
                        keyList.add(requestVerificationToken);
                    }
                }
            }

        } catch (Exception e) {
            log.error("ERROR getting cookie from WIZZ");
        }
        return result;
    }

    private String getReqStringRY(LocalDate dateOfSearch) {
        String dateOfSearchFormatted = dateOfSearch.getYear() + "-" + dateOfSearch.getMonthValue() + "-" + dateOfSearch.getDayOfMonth();
        String req = "https://www.ryanair.com/api/booking/v4/en-gb/availability?" +
                "ADT=1&TEEN=0&CHD=0&INF=0&" +
                "DateOut=" + dateOfSearchFormatted +
                "&DateIn=&Origin=" + routeMap.getOriginAirport().getCode() +
                "&Destination=" + routeMap.getDestinationAirport().getCode() +
                "&isConnectedFlight=false&RoundTrip=false&Discount=0&tpAdults=1&tpTeens=0&tpChildren=0&tpInfants=0&" +
                "tpStartDate=" + dateOfSearchFormatted +
                "&tpEndDate=&tpOriginIata=" + routeMap.getOriginAirport().getCode() +
                "&tpDestinationIata=" + routeMap.getDestinationAirport().getCode() +
                "&ToUs=AGREED" +
                "&tpIsConnectedFlight=false&tpIsReturn=false&tpDiscount=0";
        return req;
    }

    private String getDateStringWIZZ(LocalDate dateOfSearch) {
        String month = dateOfSearch.getMonthValue() < 10 ? "0" + dateOfSearch.getMonthValue() : "" + dateOfSearch.getMonthValue();
        String day = dateOfSearch.getDayOfMonth() < 10 ? "0" + dateOfSearch.getDayOfMonth() : "" + dateOfSearch.getDayOfMonth();
        return dateOfSearch.getYear() + "-" + month + "-" + day;
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