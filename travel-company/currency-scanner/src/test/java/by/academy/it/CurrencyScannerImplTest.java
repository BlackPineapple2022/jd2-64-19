package by.academy.it;

import by.academy.it.impl.CurrencyScannerImpl;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyScannerImplTest {

    @Test
    public void parse() {
        CurrencyScanner scanner = new CurrencyScannerImpl();
        JSONObject rates = scanner.parse();
        Boolean success = rates.getBoolean("success");
        System.err.println(rates);
        System.err.println(success);

        Assert.assertTrue(success);
    }
}