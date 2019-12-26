package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.accommodation.Accommodation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AccommodationServiceImpl implements AccommodationService {

    private static final AccommodationService INSTANCE = new AccommodationServiceImpl();
    private final List<Accommodation> accommodations = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);

    private AccommodationServiceImpl(){
    }

    public static AccommodationService getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Accommodation> getAllAccommodations() {
        return accommodations;
    }

    @Override
    public Accommodation addAccommodation(Accommodation accommodation) {
        accommodation.setId(sequence.incrementAndGet());
        accommodations.add(accommodation);
        return accommodation;
    }

    @Override
    public void deleteAccommodation(Long id) {
        accommodations.removeIf(a->a.getId().equals(id));
    }

    @Override
    public Accommodation updateAccommodation(Accommodation accommodation) {
        deleteAccommodation(accommodation.getId());
        accommodations.add(accommodation);
        return accommodation;
    }

}
