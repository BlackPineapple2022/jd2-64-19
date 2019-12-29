package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.service.global.ScheduleService;
import by.academy.it.travelcompany.service.global.ScheduleServiceImpl;
import by.academy.it.travelcompany.travelitem.airport.Airline;
import by.academy.it.travelcompany.travelitem.airport.Airport;
import by.academy.it.travelcompany.travelitem.flight.Flight;
import by.academy.it.travelcompany.service.local.FlightService;
import by.academy.it.travelcompany.service.local.FlightServiceImpl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class FlightScannerImpl implements FlightScanner {

    private static final int DELAY_REQ_RY = 2000;
    private static final int DELAY_REQ_RY_SYNC = 1200;
    private static final int DELAY_REQ_WIZZ = 100;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightScannerImpl.class);
    private static final FlightService FLIGHT_SERVICE = FlightServiceImpl.getInstance();
    private static final ScheduleService SCHEDULE_SERVICE = ScheduleServiceImpl.getInstance();

    private static final Object sync = new Object();

    private Long searchId;

    public FlightScannerImpl() {
    }

    @Override
    public Long getSearchId() {
        return searchId;
    }

    @Override
    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    @Override
    public List<Flight> parseFlightsRY(LocalDate startLocalDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination, String direction) {
        LOGGER.info("Start parsing on Ryanair.com, Starting date:" + startLocalDate + "dayQuantity: " + dayQuantityForSearchFromToday
                + "origin: " + origin + "destination: " + destination + "direction: " + direction + "searchId: " + searchId);
        final LocalDate finishLocalDate = startLocalDate.plusDays(dayQuantityForSearchFromToday);

        LocalDate currentLocalDate = LocalDate.of(startLocalDate.getYear(), startLocalDate.getMonthValue(), startLocalDate.getDayOfMonth());

        List<Flight> result = new ArrayList<>();
        while (currentLocalDate.isBefore(finishLocalDate)) {
            try {
                Thread.sleep(DELAY_REQ_RY);

                String req = getReqStringRY(currentLocalDate, origin, destination);
                JSONObject json = null;
                synchronized (sync) {
                    json = new JSONObject(readUrl(req));
                    Thread.sleep(DELAY_REQ_RY_SYNC);
                }
                JSONArray jsonTrips = json.getJSONArray("trips");
                String currency = (String) json.get("currency");
                JSONObject jsonTrip = (JSONObject) jsonTrips.get(0);
                JSONArray jsonDates = jsonTrip.getJSONArray("dates");
                JSONObject jsonDate = (JSONObject) jsonDates.get(0);
                JSONArray jsonFlights = jsonDate.getJSONArray("flights");

                for (int i = 0; i < jsonFlights.length(); i++) {
                    JSONObject jsonFlight = (JSONObject) jsonFlights.get(i);
                    JSONArray time = jsonFlight.getJSONArray("time");
                    String arriveDateTime = (String) time.get(1);
                    String departureDateTime = (String) time.get(0);
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

                    Flight f = new Flight(null, origin, destination, departureLocalDateTime, arriveLocalDateTime, Airline.RY, currency, amount, flightNumber);
                    f.setDirection(direction);
                    f.setSearchId(searchId);
                    FLIGHT_SERVICE.updateOrCreate(f);
                    result.add(f);

                }

            } catch (Exception e) {
            } finally {
              currentLocalDate = SCHEDULE_SERVICE.getNextLocalDate(currentLocalDate,Airline.RY,origin,destination);
            }
        }
        return result;
    }


    @Override
    public List<Flight> parseFlightsWIZZ(LocalDate localDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination, String direction) {
        LOGGER.debug("Start parsing WIZZ");
        List<Flight> result = new ArrayList<>();
        Map<String, List<String>> authMap = getWizzAirCookiesAndTokens();

        for (int i = 0; i < dayQuantityForSearchFromToday; i++) {

            try {
                Thread.sleep(DELAY_REQ_WIZZ);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://be.wizzair.com/10.3.0/Api/search/search");

            LocalDate localDateForSearch = localDate.plusDays(i);

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
                    origin.getCode() +
                    "\",\"arrivalStation\":\"" +
                    destination.getCode() +
                    "\",\"departureDate\":\"" +
                    getDateStringWIZZ(localDateForSearch) +
                    "\"}],\"adultCount\":1,\"childCount\":0,\"infantCount\":0,\"wdc\":true}";

            try {
                httpEntity = new StringEntity(stringEntity);
                httpPost.setEntity(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
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
                    String flightN = jsonOutBoundFlight.getString("carrierCode") + " " + jsonOutBoundFlight.getString("flightNumber");

                    String arriveDateTime = jsonOutBoundFlight.getString("arrivalDateTime");
                    String departureDateTime = jsonOutBoundFlight.getString("departureDateTime");

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

                    JSONArray jsonFares = jsonOutBoundFlight.getJSONArray("fares");
                    JSONObject jsonFare = (JSONObject) jsonFares.get(3);

                    JSONObject jsonBasePrice = jsonFare.getJSONObject("basePrice");
                    Double amount = (Double) jsonBasePrice.get("amount");
                    String currencyCode = (String) jsonBasePrice.get("currencyCode");

                    Flight f = new Flight(null, origin, destination, departureLocalDateTime, arriveLocalDateTime, Airline.WIZZ, currencyCode, amount, flightN);
                    f.setDirection(direction);
                    f.setSearchId(searchId);
                    FLIGHT_SERVICE.updateOrCreate(f);
                    result.add(f);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//*************************************PRIVATE METHODS****************************************************************//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Map<String, List<String>> getWizzAirCookiesAndTokens() {
        Map<String, List<String>> result = new HashMap<>();
        List<String> listOfValue = new ArrayList<>();
        List<String> keyList = new ArrayList<>();

        result.put("Set-Cookie", listOfValue);
        result.put("x-requestverificationtoken", keyList);

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest httpGet = new HttpPost("https://be.wizzair.com/10.3.0/Api/search/search");

        httpGet.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpGet.setHeader("accept-encoding", "gzip, deflate, br");
        httpGet.setHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.setHeader("cache-control", "no-cache");
        httpGet.setHeader("pragma", "no-cache");
        httpGet.setHeader("sec-fetch-mode", "navigate");
        httpGet.setHeader("sec-fetch-site", "none");
        httpGet.setHeader("sec-fetch-user", "?1");
        httpGet.setHeader("upgrade-insecure-requests", "1");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");

        try (
                CloseableHttpResponse response1 = httpClient.execute(httpGet)
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

        }
        return result;
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


    private static String getDateStringWIZZ(LocalDate dateOfSearch) {
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
}