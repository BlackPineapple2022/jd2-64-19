package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.User;

public interface UserService {

    void create(User user);

    User findByUserNameAndPassword(String userName, String password);

    Boolean isExist(String userName);

}
