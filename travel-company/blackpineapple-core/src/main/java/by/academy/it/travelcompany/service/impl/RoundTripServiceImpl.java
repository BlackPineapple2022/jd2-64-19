package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Favourite;
import by.academy.it.travelcompany.entity.RoundTrip;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.repository.FavouriteRepository;
import by.academy.it.travelcompany.repository.RoundTripRepository;
import by.academy.it.travelcompany.repository.UserRepository;
import by.academy.it.travelcompany.service.RoundTripService;
import by.academy.it.travelcompany.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoundTripServiceImpl implements RoundTripService {

    @Autowired
    RoundTripRepository roundTripRepository;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addToFavourite(RoundTrip roundTrip, User user) {
        log.info("Adding trip to favourite " + roundTrip);
        roundTrip.setId(null);
        roundTrip = roundTripRepository.save(roundTrip);
        Favourite favourite = favouriteRepository.getByUser(user);
        List<RoundTrip> roundTrips = roundTripRepository.findAllByFavouriteListContains(favourite);
        roundTrips.add(roundTrip);
        favourite.setRoundTripList(roundTrips);
        favouriteRepository.save(favourite);
    }

    @Override
    public void removeFromFavourite(RoundTrip roundTrip, User user) {
        log.info("Deleting trip from favourite " + roundTrip);
        roundTrip = roundTripRepository.findById(roundTrip.getId()).get();
        Favourite favourite = favouriteRepository.getByUser(user);
        List<RoundTrip> roundTrips = roundTripRepository.findAllByFavouriteListContains(favourite);
        roundTrips.remove(roundTrip);
        favourite.setRoundTripList(roundTrips);
        favouriteRepository.save(favourite);
    }

    @Override
    public List<RoundTrip> getFavouriteRoundTripList(User user) {

        Favourite favourite = favouriteRepository.getByUser(user);

        return roundTripRepository.findAllByFavouriteListContains(favourite);

    }
}
