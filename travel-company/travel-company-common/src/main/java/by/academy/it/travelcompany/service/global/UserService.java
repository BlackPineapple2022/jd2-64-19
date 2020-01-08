package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.user.User;

import java.util.Optional;

public interface UserService {

    Optional <User> findUserByLoginAndPassword(String login, String password);

    Optional <User> findUserById(Long id);



}
