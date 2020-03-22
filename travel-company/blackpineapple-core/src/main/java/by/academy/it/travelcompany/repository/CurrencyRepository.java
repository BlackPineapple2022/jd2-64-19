package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface CurrencyRepository extends CrudRepository<Currency, Serializable> {

    Currency findByCode(String code);

}
