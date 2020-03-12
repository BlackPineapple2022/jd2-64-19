package by.academy.it.travelcompany.provider.impl;

import by.academy.it.travelcompany.provider.RouteMapStringProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RouteMapStringProviderImpl implements RouteMapStringProvider {

    @Override
    public String getRouteMapStringWithOlderScanningByAirline(String airlineName) {
        log.info("Getting route map with older scanning by airline "+ airlineName);
        String address = apiAddress+"flightjournals/finder/";
        Set<LocalDate> localDateSet = new HashSet<>();
        JSONObject jsonObject = null;
        try {
            return readUrl(address+airlineName);
        } catch (Exception e) {
            log.error("Error while getting route map from remote server",e);
            return null;
        }
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
