package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.converter.dtotoentity.FlightConverter;
import by.academy.it.travelcompany.customs.FavouriteTripUpdaterCustomsInformer;
import by.academy.it.travelcompany.dto.FlightDTO;
import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RoundTrip;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.sender.SenderService;
import by.academy.it.travelcompany.service.FlightService;
import by.academy.it.travelcompany.service.RoundTripService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@Slf4j
public class UpdateTripController implements SenderService {

    @Autowired
    private FavouriteTripUpdaterCustomsInformer favouriteTripUpdaterCustomsInformer;

    @Autowired
    private RoundTripService roundTripService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private FlightConverter flightConverter;

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {

        httpSession.removeAttribute("trips");

        Object userObject = httpSession.getAttribute("user");

        if (userObject!=null){
            User user = (User)userObject;
            List<RoundTrip> favouriteRoundTripList = roundTripService.getFavouriteRoundTripList(user);
            httpSession.setAttribute("trips",favouriteRoundTripList);
        }

        return "update";

    }

    @RequestMapping(value = "/updateNow", method = RequestMethod.GET)
    public String updateNow() {

        User user = (User) httpSession.getAttribute("user");

        if (favouriteTripUpdaterCustomsInformer.isActive(user)) {
            return "redirect:/favourite";
        }

        favouriteTripUpdaterCustomsInformer.start(user);

        Set<FlightDTO> updateSet = new HashSet<>();
        List<RoundTrip> roundTrips = roundTripService.getFavouriteRoundTripList(user);

        for (RoundTrip roundTrip : roundTrips) {
            updateSet.add(flightConverter.convert(roundTrip.getDirectFlight()));
            updateSet.add(flightConverter.convert(roundTrip.getReturnFlight()));
        }

        List<FlightDTO> updateList = new ArrayList<>(updateSet);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (FlightDTO flightDTO : updateList) {
            Map<String, String> map = new HashMap();
            map.put("routeMap", flightDTO.getRouteMap());
            map.put("departureDateTime", flightDTO.getDepartureDateTime());
            map.put("arriveDateTime", flightDTO.getArriveDateTime());
            map.put("flightNumber", flightDTO.getFlightNumber());
            map.put("currency", flightDTO.getCurrency());
            map.put("amount", flightDTO.getAmount().toString());
            JSONObject updatedJSON = new JSONObject(map);
            jsonObjects.add(updatedJSON);
        }

        log.info("Sending flights to remote server (updater)" + updateList);

        String address = apiAddress + "flights/";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(address);
        HttpEntity httpEntity;

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

        try {
            httpEntity = new StringEntity(jsonObjects.toString());
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            String stringEntity = convertInputStreamToString(responseEntity.getContent());

            JSONArray jsonArray = new JSONArray(stringEntity);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                FlightDTO flightDTO = new FlightDTO();
                flightDTO.setRouteMap(jsonObject.getString("routeMap"));
                flightDTO.setCurrency(jsonObject.getString("currency"));
                flightDTO.setFlightNumber(jsonObject.getString("flightNumber"));
                flightDTO.setAmount(jsonObject.getDouble("amount"));
                flightDTO.setArriveDateTime(jsonObject.getString("arriveDateTime"));
                flightDTO.setDepartureDateTime(jsonObject.getString("departureDateTime"));
                Flight flight = flightConverter.convert(flightDTO);
                flightService.createOrUpdate(flight);
            }

            httpClient.close();
        } catch (Exception e) {
            log.error("Error while sending flights to remote server (Main)", e);
        } finally {
            favouriteTripUpdaterCustomsInformer.stop(user);
        }
        return "redirect:/favourite";
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

    private void createOrUpdateFlight(
            @RequestBody List<FlightDTO> flightDTOs) {

        for (FlightDTO flightDTO : flightDTOs) {
            Flight flight = flightConverter.convert(flightDTO);
            flightService.createOrUpdate(flight);
        }

    }
}
