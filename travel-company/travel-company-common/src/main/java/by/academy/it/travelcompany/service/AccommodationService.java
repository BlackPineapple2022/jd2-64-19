package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.accommodation.Accommodation;

import java.util.List;

/**
 * Accommodation service
 */

public interface AccommodationService {

    /**
     * Get all accommodation
     * @return list of found accommodation
     */

    List<Accommodation> getAllAccommodations();

    /**
     * Add new accommodation
     * @param accommodation accommodation to save
     * @return new accommodation with generated Id
     */

    Accommodation addAccommodation(Accommodation accommodation);

    /**
     * Delete accommodation by id
     * accommodation with @param id delete from list
     */

    void deleteAccommodation();

    /**
     * Update object in list with same id of
     * @param accommodation accommodation to update (by id)
     * @return accommodation updated accommodation
     */

    Accommodation updateAccommodation(Accommodation accommodation);

  }
