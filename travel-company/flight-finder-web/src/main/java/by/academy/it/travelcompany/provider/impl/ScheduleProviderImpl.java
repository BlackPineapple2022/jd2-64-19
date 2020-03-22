package by.academy.it.travelcompany.provider.impl;

import by.academy.it.travelcompany.Schedule;
import by.academy.it.travelcompany.provider.ScheduleProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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
public class ScheduleProviderImpl implements ScheduleProvider {

    @Override
    public Schedule getScheduleByRouteMapString(String routeMapString) {
        log.info("Getting schedule for route map "+ routeMapString);
        String address = apiAddress+"schedule/";
        Set<LocalDate> localDateSet = new HashSet<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(readUrl(address+routeMapString));
        } catch (Exception e) {
            log.error("Error while getting schedule from remote server",e);
            return null;
        }
        JSONArray dateTimeArray = jsonObject.getJSONArray("dateTime");
        for (Object o : dateTimeArray) {
            LocalDate date = LocalDate.parse((String) o);
            localDateSet.add(date);
        }
        return new Schedule(localDateSet);
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
