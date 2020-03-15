package by.academy.it.travelcompany.customs.impl;

import by.academy.it.travelcompany.customs.FavouriteTripUpdaterCustomsInformer;
import by.academy.it.travelcompany.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FavouriteTripUpdaterCustomsInformerImpl implements FavouriteTripUpdaterCustomsInformer {

    private static final Set<User> activeUpdaterUserSet = new HashSet<>();

    @Override
    public void start(User user) {
        activeUpdaterUserSet.add(user);
    }

    @Override
    public void stop(User user) {
        activeUpdaterUserSet.removeIf(u->u.equals(user));
    }

    @Override
    public Boolean isActive(User user) {
        return activeUpdaterUserSet.contains(user);
    }


}
