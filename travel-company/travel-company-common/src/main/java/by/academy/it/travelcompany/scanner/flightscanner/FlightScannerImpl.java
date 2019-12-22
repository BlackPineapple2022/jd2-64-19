package by.academy.it.travelcompany.scanner.flightscanner;

import by.academy.it.travelcompany.airport.Airline;
import by.academy.it.travelcompany.airport.Airport;
import by.academy.it.travelcompany.flight.Flight;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.FlightServiceImpl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public class FlightScannerImpl extends Thread implements FlightScanner {

// RY API address for getting json:
// https://www.ryanair.com/api/booking/v4/en-gb/

// example request:
// https://www.ryanair.com/api/booking/v4/en-gb/availability?ADT=1&TEEN=0&CHD=0&INF=0&DateOut=2019-12-21&DateIn=&Origin=VNO&Destination=BGY&isConnectedFlight=false&RoundTrip=false&Discount=0&tpAdults=1&tpTeens=0&tpChildren=0&tpInfants=0&tpStartDate=2019-12-21&tpEndDate=&tpOriginIata=VNO&tpDestinationIata=BGY&ToUs=AGREED&tpIsConnectedFlight=false&tpIsReturn=false&tpDiscount=0
//
// example response:
// {"termsOfUse":"https://www.ryanair.com/ie/en/corporate/terms-of-use=AGREED",
// "currency":"EUR","currPrecision":2,"trips":[{"origin":"VNO","originName":"Vilnius",
// "destination":"BGY","destinationName":"Milan (Bergamo)",
// "routeGroup":"CITY","tripType":"CITY_BREAK","upgradeType":
// "PLUS","dates":[{"dateOut":"2019-12-21T00:00:00.000","flights":
// [{"faresLeft":3,"flightKey":"FR~2871~ ~~VNO~12/21/2019
// 21:50~BGY~12/21/2019 23:20~~","infantsLeft":16,"regularFare"
// :{"fareKey":"RJ2GYYYJH6UJRR3I6CEYIIU7I6TMXZXQPFFJXBRET4GD6A3PXCDQXFOB6ZESSDH6IBIF7MBVYKB33PDBJI2T3UA7WCMTIU4R7ZLMI5252X7ROIA666QNSHXVF764PZR45JMAYRX4EKMM4DOKIJJZJLBW4YWZN6QQBDFUKCL2U2N32VPNQYXA"
// ,"fareClass":"A","fares":[{"type":"ADT","amount":39.9900,"count":1,"hasDiscount":
// false,"publishedFare":39.9900,"discountInPercent":0,"hasPromoDiscount":
// false,"discountAmount":0.0}]},"operatedBy":"","segments":[{"segmentNr":
// 0,"origin":"VNO","destination":"BGY","flightNumber":"FR 2871","time":
// ["2019-12-21T21:50:00.000","2019-12-21T23:20:00.000"],"timeUTC":
// ["2019-12-21T19:50:00.000Z","2019-12-21T22:20:00.000Z"],
// "duration":"02:30"}],"flightNumber":"FR 2871","time":
// ["2019-12-21T21:50:00.000","2019-12-21T23:20:00.000"],
// "timeUTC":["2019-12-21T19:50:00.000Z","2019-12-21T22:20:00.000Z"],
// "duration":"02:30"}]}]}],"serverTimeUTC":"2019-12-20T18:45:08.773Z"}


    private static final int DELAYREQRY = 10000;
    private static final int DELAYREQWIZZ = 10;
    private static final int CONNECTION_TIMEOUT = 5000;

    private static final FlightService flightService = FlightServiceImpl.getInstance();

    public FlightScannerImpl() {
    }

    @Override
    public void parseFlights(Airline airline, LocalDate startLocalDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination) {
    }

    @Override
    public void parseFlightsRY(LocalDate startLocalDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination) {
        for (int j = 0; j < dayQuantityForSearchFromToday; j++) {
            try {
                Thread.sleep(DELAYREQRY);

                String req = getReqStringRY(startLocalDate.plusDays(j), origin, destination);

                JSONObject json = new JSONObject(readUrl(req));
                JSONArray jsonTrips = json.getJSONArray("trips");
                String currency = (String) json.get("currency");
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

                    Flight f = new Flight(null, origin, destination, arriveLocalDateTime, departureLocalDateTime, Airline.RY, currency, amount, flightNumber);
                    System.out.println(f);
                    flightService.updateOrCreate(f);
                    System.out.println(f);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void parseFlightsWIZZ(LocalDate localDate, Integer dayQuantityForSearchFromToday, Airport origin, Airport destination) throws UnsupportedEncodingException, InterruptedException {

        Map<String, List<String>> authMap = getWizzAirCookiesAndTokens();

        // System.out.println("1");
        // System.out.println(authMap.get("x-requestverificationtoken").get(0));
        // for (int k = 0;k<authMap.get("Set-Cookie").size();k++){
        //    System.out.println(authMap.get("Set-Cookie").get(k));
        //}
        // System.out.println("1");

        for (int i = 0; i < dayQuantityForSearchFromToday; i++) {
            Thread.sleep(DELAYREQWIZZ);
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

            // System.out.println("2");
            // Header[] headers = httpPost.getAllHeaders();

            // for (Header h:headers) {
            //    System.out.println(h.getName()+":**********:"+h.getValue());
            //}
            // System.out.println("2");

            HttpEntity httpEntity;// = null;

            String stringEntity = "{\"isFlightChange\":false,\"isSeniorOrStudent\":false,\"flightList\":[{\"departureStation\":\"" +
                    origin.getCode() +
                    "\",\"arrivalStation\":\"" +
                    destination.getCode() +
                    "\",\"departureDate\":\"" +
                    getDateStringWIZZ(localDateForSearch) +
                    "\"}],\"adultCount\":1,\"childCount\":0,\"infantCount\":0,\"wdc\":true}";

            //System.out.println(stringEntity);

            httpEntity = new StringEntity(stringEntity);

            httpPost.setEntity(httpEntity);

            try (
                    CloseableHttpResponse response = httpClient.execute(httpPost)
            ) {
                //Header[] headersResp = response.getAllHeaders();
                //System.out.println("3");
                //for (Header h : headersResp) {
                //    System.out.println(h.getName() + "******-*********" + h.getValue());
                //
                //}

                HttpEntity responseEntity = response.getEntity();
                String responseBodyString = convertInputStreamToString(responseEntity.getContent());
                //System.out.println(responseBodyString);
                JSONObject json = new JSONObject(responseBodyString);

                JSONArray jsonOutBoundFlights = json.getJSONArray("outboundFlights");
                System.out.println(jsonOutBoundFlights);

                for (int l = 0;l<jsonOutBoundFlights.length();l++){
                    JSONObject jsonOutBoundFlight = (JSONObject) jsonOutBoundFlights.get(l);
                    String flightN = jsonOutBoundFlight.getString("carrierCode")+" "+jsonOutBoundFlight.getString("flightNumber");
                    System.out.println(flightN);
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

                    System.out.println(arriveLocalDateTime);
                    System.out.println(departureLocalDateTime);

                }

                //System.out.println(convertInputStreamToString(responseEntity.getContent()));
                //System.out.println("3");

            } catch (Exception e) {

            }

        }
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