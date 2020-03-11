package by.academy.it.impl;

import by.academy.it.FlightScanner;
import by.academy.it.FlightScannerData;
import by.academy.it.Schedule;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;


@Slf4j
public class WIZZFlightScanner implements FlightScanner {

    private Set<Long> timeStampFlightFindSet = new TreeSet<>();

    private static final Object SYNC_WIZZ = new Object();

    private Schedule schedule = new Schedule();

    private List<FlightScannerData> flightScannerDataList;

    private static String parseURL = "https://be.wizzair.com/10.15.1/Api/search/search";

    public static String getParseURL() {
        return parseURL;
    }

    public static void setParseURL(String parseURL) {
        WIZZFlightScanner.parseURL = parseURL;
    }

    public WIZZFlightScanner(FlightScannerData data) {
        this.flightScannerDataList = new ArrayList<>();
        this.flightScannerDataList.add(data);
    }

    public WIZZFlightScanner(List<FlightScannerData> dataList) {
        this.flightScannerDataList = dataList;
    }

    public WIZZFlightScanner(List<FlightScannerData> dataList, Schedule schedule) {
        this(dataList);
        this.schedule = schedule;
    }

    public WIZZFlightScanner(FlightScannerData data, Schedule schedule) {
        this(data);
        this.schedule = schedule;
    }

    public Set<Long> getTimeStampFlightFindSet() {
        return timeStampFlightFindSet;
    }

    @Override
    public List<JSONObject> parse(Long requestTimeout, Integer multiplierTimeout) {


        List<JSONObject> result = new ArrayList<>();
        Map<String, List<String>> authMap = new HashMap<>();

        try {
            authMap = getWizzAirCookiesAndTokens();
        } catch (Exception e) {
            return null;
        }

        for (FlightScannerData flightScannerData : flightScannerDataList) {

            LocalDate startingDate = flightScannerData.getStartingDate();
            Integer dayCount = flightScannerData.getDayCount();
            String routeMap = flightScannerData.getRouteMap();

            String originAirport = routeMap.split("--")[1];
            String destinationAirport = routeMap.split("--")[2];

            log.info("Start parsing on Wizzair.com, starting date: " + startingDate + " dayCount: " + dayCount + " RouteMap " + routeMap);

            final LocalDate finishLocalDate = startingDate.plusDays(dayCount);
            LocalDate currentLocalDate = LocalDate.of(startingDate.getYear(), startingDate.getMonthValue(), startingDate.getDayOfMonth());

            while (currentLocalDate.isBefore(finishLocalDate.plusDays(1))) {

                synchronized (SYNC_WIZZ) {
                    try {
                        Thread.sleep((long) (requestTimeout * (1.0 + (multiplierTimeout * Math.random()))));
                    } catch (InterruptedException e) {
                        log.error("Interrupted while sleep", e);
                    }
                }

                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(parseURL);

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
                        originAirport +
                        "\",\"arrivalStation\":\"" +
                        destinationAirport +
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
                        String currencyStr = (String) jsonBasePrice.get("currencyCode");

                        Map<String, String> map = new HashMap();
                        map.put("routeMap", routeMap);
                        map.put("departureDateTime", departureDateTime);
                        map.put("arriveDateTime", arriveDateTime);
                        map.put("flightNumber", flightNumber);
                        map.put("currency", currencyStr);
                        map.put("amount", amount.toString());
                        JSONObject foundFlight = new JSONObject(map);

                        result.add(foundFlight);
                        log.info("Flight found: " + foundFlight);
                    }

                } catch (Exception e) {
                    log.error("Error getting flight, this date WIZZ doesn't fly by this route: " + currentLocalDate, e);
                } finally {
                    currentLocalDate = schedule.getNextDate(currentLocalDate);
                    timeStampFlightFindSet.add(System.currentTimeMillis());
                }
            }
        }
        log.info("Flight scanning successfully ended");
        return result;
    }

    private Map<String, List<String>> getWizzAirCookiesAndTokens() {
        Map<String, List<String>> result = new HashMap<>();
        List<String> listOfValue = new ArrayList<>();
        List<String> keyList = new ArrayList<>();

        result.put("Set-Cookie", listOfValue);
        result.put("x-requestverificationtoken", keyList);

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest httpPost = new HttpPost(parseURL);

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

    private String getDateStringWIZZ(LocalDate dateOfSearch) {
        String month = dateOfSearch.getMonthValue() < 10 ? "0" + dateOfSearch.getMonthValue() : "" + dateOfSearch.getMonthValue();
        String day = dateOfSearch.getDayOfMonth() < 10 ? "0" + dateOfSearch.getDayOfMonth() : "" + dateOfSearch.getDayOfMonth();
        return dateOfSearch.getYear() + "-" + month + "-" + day;
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
