package by.academy.it.travelcompany.install.currency.impl;

import by.academy.it.travelcompany.entity.Currency;
import by.academy.it.travelcompany.install.currency.CurrencyInstaller;
import by.academy.it.travelcompany.install.currency.data.CurrencyEnum;
import by.academy.it.travelcompany.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrencyInstallerImpl implements CurrencyInstaller {

    @Autowired
    CurrencyService currencyService;

    @Override
    public void install() {
        log.info("Starting install currency");
        for (CurrencyEnum currencyEnum : CurrencyEnum.values() ) {
            currencyService.create(new Currency(currencyEnum.name()));
        }
    }
}
