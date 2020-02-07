package by.academy.it.travelcompany.service.global.imp;

import java.util.HashSet;
import java.util.Set;

public class FavouriteTripUpdaterServiceImpl {

    private final Set<Long> setOfUserIdInWork = new HashSet<>();

    private static final FavouriteTripUpdaterServiceImpl INSTANCE = new FavouriteTripUpdaterServiceImpl();

    private FavouriteTripUpdaterServiceImpl() {
    }

    public static FavouriteTripUpdaterServiceImpl getInstance() {
        return INSTANCE;
    }

    public Boolean isFreeForUserId(Long userId){
        return !setOfUserIdInWork.contains(userId);
    }

    public void startUpdater(Long userId){
        setOfUserIdInWork.add(userId);
    }

    public void finishUpdater(Long userId){
        setOfUserIdInWork.removeIf(u->u.equals(userId));
    }

}
