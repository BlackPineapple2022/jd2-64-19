package by.academy.it.travelcompany.dao;

import by.academy.it.travelcompany.user.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends DAO<User> {

    Optional<User> getByUserName(String userName) throws SQLException;

}