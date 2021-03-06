package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Currency;

public interface CurrencyService {

    void create(Currency currency);

    Currency findByCode(String code);

    Double getEURMultiplier(Currency currency);

    Double getEURMultiplier(String code);
}
