package by.academy.it.travelcompany.sender.impl;

import by.academy.it.travelcompany.sender.FlightSenderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class FlightSenderServiceImpl implements FlightSenderService {

    @Override
    public void sendData(List<JSONObject> data) {
        log.info("Sending flights to remote server (Main)"+data);
        String address = apiAddress+"flights/finder/";

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
            httpEntity = new StringEntity(data.toString());
            httpPost.setEntity(httpEntity);
            httpClient.execute(httpPost);
            httpClient.close();
        } catch (Exception e){
            log.error("Error while sending flights to remote server (Main)",e);
        }

    }
}
