package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Favourite;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.repository.FavouriteRepository;
import by.academy.it.travelcompany.service.FavouriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    FavouriteRepository favouriteRepository;

    @Override
    public Favourite create(Favourite favourite) {
        log.info("Creating new favourite");
        return favouriteRepository.save(favourite);
    }




}
