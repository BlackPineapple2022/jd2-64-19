package by.academy.it.travelcompany.scanner.currencyscaner;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CurrencyScannerImpl {

    private static final String ACCESS_KEY ="872986e602ba0f07b2dfceea07408ea1";
    private static final String CURRENCY_RATE_SOURCE = "http://data.fixer.io/api/latest";

    private static final CurrencyScannerImpl INSTANCE = new CurrencyScannerImpl();

    private CurrencyScannerImpl(){
    }

    public static CurrencyScannerImpl getInstance(){
        return INSTANCE;
    }

    public Double getEURRate(String currencyCode){

        try {
            JSONObject json = new JSONObject(readUrl(CURRENCY_RATE_SOURCE+"?access_key="+ACCESS_KEY));
            JSONObject jsonRates = json.getJSONObject("rates");
            return jsonRates.getDouble(currencyCode);

        } catch (Exception e) {
            e.printStackTrace();
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
