package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.user.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private final Map<String, User> users = new ConcurrentHashMap<>();

    private UserServiceImpl(){
        users.put("black",new User(1L,"black","123","admin"));
        users.put("white",new User(2L,"white","123","user"));
    }

    public static UserService getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<User> findUser(String login, String password) {
        User user = users.get(login);
        if (user != null && password.equals(user.getPassword())) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
