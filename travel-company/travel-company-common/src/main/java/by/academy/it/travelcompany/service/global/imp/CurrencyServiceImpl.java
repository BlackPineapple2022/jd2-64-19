package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.CurrencyDAO;
import by.academy.it.travelcompany.dao.impl.CurrencyDAOImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
@Slf4j
public class CurrencyServiceImpl {

    CurrencyDAO currencyDAO = CurrencyDAOImpl.getInstance();

    private static final CurrencyServiceImpl INSTANCE = new CurrencyServiceImpl();

    private CurrencyServiceImpl() {
    }

    public static CurrencyServiceImpl getInstance() {
        return INSTANCE;
    }

    public Long getIdByName(String code){
        log.info("Getting currency id by name from Base{}",code);
        try{
            return currencyDAO.getIdByCode(code);
        }catch (SQLException e){
            log.error("Error while getting currency id by name "+code,e);
        }
        return null;
    }


}
