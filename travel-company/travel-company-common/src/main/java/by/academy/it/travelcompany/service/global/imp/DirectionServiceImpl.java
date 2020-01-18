package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.DirectionDAO;
import by.academy.it.travelcompany.dao.impl.DirectionDAOImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class DirectionServiceImpl {
    private static final DirectionServiceImpl INSTANCE = new DirectionServiceImpl();
    private final DirectionDAO directionDAO = DirectionDAOImpl.getInstance();
    private DirectionServiceImpl(){
    }
    public static DirectionServiceImpl getInstance(){
        return INSTANCE;
    }

    public Long getIdByName(String name){
        log.info("Getting direction id by name from Base{}",name);
        try{
           return directionDAO.getIdByName(name);
        }catch (SQLException e){
            log.error("Error while getting direction id by name "+name,e);
        }
        return null;
    }



}
