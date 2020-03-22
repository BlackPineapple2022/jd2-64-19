package by.academy.it.travelcompany.customs;

import by.academy.it.travelcompany.entity.User;

public interface FavouriteTripUpdaterCustomsInformer {

    void start(User user);

    void stop(User user);

    Boolean isActive (User user);

}
