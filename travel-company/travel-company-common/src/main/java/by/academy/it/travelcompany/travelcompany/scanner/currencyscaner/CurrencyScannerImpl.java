package by.academy.it.travelcompany.travelcompany.scanner.currencyscaner;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

@Slf4j
public class CurrencyScannerImpl implements CurrencyScanner {

    private static final String SITE_ADDRESS ="http://blackpineapple.by/travel"; //TODO change site address before deployment!

    private static final String ACCESS_KEY = "872986e602ba0f07b2dfceea07408ea1";
    private static final String CURRENCY_RATE_SOURCE = "http://data.fixer.io/api/latest";

    private static final CurrencyScanner INSTANCE = new CurrencyScannerImpl();

    private static JSONObject currencyRateJson = getCurrencyRateJson();
    private static JSONObject currencyRateJsonLocal = getCurrencyRateJsonLocal();


    private CurrencyScannerImpl() {
    }

    public static CurrencyScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public Double getEURMultiplier(String currencyCode) {
        Double multiplier = 0.0;
        try {
            multiplier = 1 / currencyRateJson.getJSONObject("rates").getDouble(currencyCode);
        } catch (Exception e) {
            multiplier = 1 / currencyRateJsonLocal.getJSONObject("rates").getDouble(currencyCode);
        }
        return multiplier;
    }

    @Override
    public void update() {
        try {
            log.info("Currency rate updating");
            JSONObject jsonObject = new JSONObject(readUrl(CURRENCY_RATE_SOURCE + "?access_key=" + ACCESS_KEY));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            log.info("Currency rate updated successful");
            currencyRateJson = jsonObject;
        } catch (Exception e) {
            log.error("Updating currency rate error", e);
        }
    }

    private static JSONObject getCurrencyRateJson() {
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

    private static JSONObject getCurrencyRateJsonLocal() {
        try {
            log.info("Local currency rate initializing");
            JSONObject jsonObject = new JSONObject((readUrl(SITE_ADDRESS+"/resources/currency_local_json.txt")));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            log.info("Local currency rate initialized successful");
        } catch (Exception e) {
            log.error("Initializing local currency rate error", e);
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
