package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Currency;
import by.academy.it.travelcompany.repository.CurrencyRepository;
import by.academy.it.travelcompany.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public void create(Currency currency) {
        log.info("Creating currency" + currency);
        currencyRepository.save(currency);
    }

    @Override
    public Currency findByCode(String code) {
       log.info("Finding currency by code" + code);
       return currencyRepository.findByCode(code);
    }
}
