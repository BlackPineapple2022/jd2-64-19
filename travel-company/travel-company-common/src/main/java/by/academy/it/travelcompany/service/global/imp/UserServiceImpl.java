package by.academy.it.travelcompany.service.global.imp;

import by.academy.it.travelcompany.travelcompany.dao.UserDAO;
import by.academy.it.travelcompany.travelcompany.dao.impl.UserDAOImpl;
import by.academy.it.travelcompany.security.EncryptUtils;
import by.academy.it.travelcompany.service.global.UserService;
import by.academy.it.travelcompany.travelcompany.user.User;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();

    private final UserDAO userDAO = UserDAOImpl.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        try {
            Optional<User> userOption = userDAO.getByUserName(login);
            if (userOption.isPresent()) {
                User user = userOption.get();
                String hash = EncryptUtils.getSHA256(password, user.getSalt());
                if (user.getPassword().equals(hash)) {
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error find user by login and password " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try {
            return userDAO.read(id);
        } catch (SQLException e) {
            log.error("Error find user by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        log.info("add new user {}", user);
        try {
            Long id = userDAO.create(user);
            user.setId(id);
            FavouriteServiceImpl.getInstance().newUser(user.getUserName(),id);
            //When user creating new favourite creating with name userName_favourite
            log.info("result {}", id);
        } catch (SQLException e) {
            log.error("Error while creating user " + user, e);
        }
        return user;
    }

    @Override
    public Boolean isUserNameFree(String userName) {
        try {
            Optional<User> userOption = userDAO.getByUserName(userName);
            if (userOption.isPresent()) {
                return false;
            }
        } catch (SQLException e) {
            log.error("Error find user by userName" + userName, e);
        }
        return true;
    }
}