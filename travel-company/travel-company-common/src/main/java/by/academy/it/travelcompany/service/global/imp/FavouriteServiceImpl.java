package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.dao.FavouriteDAO;
import by.academy.it.travelcompany.dao.impl.FavouriteDAOImpl;
import by.academy.it.travelcompany.favourite.Favourite;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Slf4j
public class FavouriteServiceImpl {
    private static final FavouriteServiceImpl INSTANCE = new FavouriteServiceImpl();
    private final FavouriteDAO favouriteDAO = FavouriteDAOImpl.getInstance();
    private FavouriteServiceImpl(){
    }
    public static FavouriteServiceImpl getInstance(){
        return INSTANCE;
    }

    public void newUser(String userName, Long id){
        log.info("Creating new user, adding favourite list to base",userName);
        try{
            favouriteDAO.newUser(userName,id);
        }catch (SQLException e){
            log.error("Error while adding favourite list to base",e);
        }

    }

    public void newFavourite(String favouriteName, Long id){
        log.info("Creating new favourite, adding favourite list to base",favouriteName);
        try{
            favouriteDAO.newFavourite(favouriteName,id);
        }catch (SQLException e){
            log.error("Error while adding favourite list to base",e);
        }

    }

    public List<Favourite> getAllFavouriteByUserId(Long userId){
        log.info("Getting favourites from Base{} by userId", userId);
        try{
            return favouriteDAO.getAllByUserId(userId);
        } catch (SQLException e){
            log.error("Error while getting favourites by user id: "+ userId, e);
        }
        return Collections.emptyList();
    }


}
