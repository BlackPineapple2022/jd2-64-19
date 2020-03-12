package by.academy.it.travelcompany.scanner.impl;

import by.academy.it.travelcompany.CurrencyScanner;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
public class CurrencyScannerImpl implements CurrencyScanner {

    private static final String ACCESS_KEY = "872986e602ba0f07b2dfceea07408ea1";
    private static final String CURRENCY_RATE_SOURCE = "http://data.fixer.io/api/latest";

    @Override
    public JSONObject parse() {
        try {
            log.info("Currency rate initializing");
            JSONObject jsonObject = new JSONObject(readUrl(CURRENCY_RATE_SOURCE + "?access_key=" + ACCESS_KEY));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            log.info("Currency rate initialized successful");
            return jsonObject;
        } catch (Exception e) {
            log.error("Initializing currency rate error", e);
        }
        return null;
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
