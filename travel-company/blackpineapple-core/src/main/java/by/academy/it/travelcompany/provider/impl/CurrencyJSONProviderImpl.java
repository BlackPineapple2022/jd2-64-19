package by.academy.it.travelcompany.provider.impl;

import by.academy.it.travelcompany.CurrencyScanner;
import by.academy.it.travelcompany.provider.CurrencyJSONProvider;
import by.academy.it.travelcompany.scanner.impl.CurrencyScannerImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyJSONProviderImpl implements CurrencyJSONProvider {

    @Autowired
    private CurrencyScanner currencyScanner;

    private JSONObject currencyJson = new CurrencyScannerImpl().parse();

    @Override
    public JSONObject getCurrencyRateJSON() {
        return currencyJson;
    }

    public CurrencyJSONProviderImpl() {
    }

    @Scheduled(fixedDelay = 24*60*60*1000)
    private void update(){
        log.info("UPDATING CURRENCY RATES");
        currencyJson = new CurrencyScannerImpl().parse();
    }
}
