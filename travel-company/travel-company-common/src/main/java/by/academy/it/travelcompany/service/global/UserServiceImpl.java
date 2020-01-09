package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.dao.UserDAO;
import by.academy.it.travelcompany.dao.UserDAOImpl;
import by.academy.it.travelcompany.security.EncryptUtils;
import by.academy.it.travelcompany.user.User;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
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
            LOGGER.error("Error find user by login and password " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try {
            return userDAO.read(id);
        } catch (SQLException e) {
            LOGGER.error("Error find user by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public User add(User user) {
        LOGGER.info("add new user {}", user);
        try {
            Long id = userDAO.create(user);
            user.setId(id);
            LOGGER.info("result {}", id);
        } catch (SQLException e) {
            LOGGER.error("Error while creating student " + user, e);
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
            LOGGER.error("Error find user by userName" + userName, e);
        }
        return true;
    }
}