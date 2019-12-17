package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.user.User;

import java.util.Optional;

public interface UserService {

    Optional <User> findUser(String login,String password);

}
