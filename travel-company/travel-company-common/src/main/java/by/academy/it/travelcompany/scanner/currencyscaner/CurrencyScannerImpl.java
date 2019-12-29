package by.academy.it.travelcompany.scanner.currencyscaner;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

public class CurrencyScannerImpl implements CurrencyScanner {

    private static final String SITE_ADDRESS ="http://localhost:8080/myapp";

    private static final String ACCESS_KEY = "1eb5276f5726efc74fbf3799adcf7973";
    private static final String CURRENCY_RATE_SOURCE = "http://data.fixer.io/api/latest";

    private static final CurrencyScanner INSTANCE = new CurrencyScannerImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyScannerImpl.class);

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
            LOGGER.info("Currency rate updating");
            JSONObject jsonObject = new JSONObject(readUrl(CURRENCY_RATE_SOURCE + "?access_key=" + ACCESS_KEY));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            LOGGER.info("Currency rate updated successful");
            currencyRateJson = jsonObject;
        } catch (Exception e) {
            LOGGER.error("Updating currency rate error", e);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//*************************************PRIVATE METHODS****************************************************************//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static JSONObject getCurrencyRateJson() {
        try {
            LOGGER.info("Currency rate initializing");
            JSONObject jsonObject = new JSONObject(readUrl(CURRENCY_RATE_SOURCE + "?access_key=" + ACCESS_KEY));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            LOGGER.info("Currency rate initialized successful");
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("Initializing currency rate error", e);
        }
        return null;
    }

    private static JSONObject getCurrencyRateJsonLocal() {
        try {
            LOGGER.info("Local currency rate initializing");
            JSONObject jsonObject = new JSONObject((readUrl(SITE_ADDRESS+"/resources/currency_local_json.txt")));
            JSONObject jsonRates = jsonObject.getJSONObject("rates");
            LOGGER.info("Local currency rate initialized successful");
        } catch (Exception e) {
            LOGGER.error("Initializing local currency rate error", e);
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
