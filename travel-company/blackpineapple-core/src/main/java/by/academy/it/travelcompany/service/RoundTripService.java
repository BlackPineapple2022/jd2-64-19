package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.RoundTrip;
import by.academy.it.travelcompany.entity.User;

import java.util.List;

public interface RoundTripService {

    void addToFavourite(RoundTrip roundTrip, User user);

    void removeFromFavourite(RoundTrip roundTrip, User user);

    List<RoundTrip> getFavouriteRoundTripList(User user);
}
