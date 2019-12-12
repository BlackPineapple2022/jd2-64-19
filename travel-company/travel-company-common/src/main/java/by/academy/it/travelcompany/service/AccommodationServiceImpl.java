package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.accommodation.Accommodation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AccommodationServiceImpl implements AccommodationService {

    private static final AccommodationService INSTANCE = new AccommodationServiceImpl();
    private static final List<Accommodation> accommodations = new ArrayList<>();
    private static final AtomicLong sequence = new AtomicLong();

    private AccommodationServiceImpl(){
    }

    @Override
    public List<Accommodation> getAllAccommodations() {
        return null;
    }

    @Override
    public Accommodation addAccommodation(Accommodation accommodation) {
        return null;
    }

    @Override
    public void deleteAccommodation() {

    }

    @Override
    public Accommodation updateAccommodation(Accommodation accommodation) {
        return null;
    }

}
