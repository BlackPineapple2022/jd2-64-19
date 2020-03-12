package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Favourite;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.repository.FavouriteRepository;
import by.academy.it.travelcompany.repository.UserRepository;
import by.academy.it.travelcompany.security.EncryptUtils;
import by.academy.it.travelcompany.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Override
    public void create(User user) {
        log.info("Encrypting password and generating salt");
        String salt = EncryptUtils.generateSaltString();
        String encryptPass = EncryptUtils.getSHA256(user.getPassword(),salt);
        Favourite favourite = new Favourite(user);
        user.setPassword(encryptPass);
        user.setSalt(salt);
        user.setFavourite(favourite);
        log.info("Creating new user");
        userRepository.save(user);
    }
}
