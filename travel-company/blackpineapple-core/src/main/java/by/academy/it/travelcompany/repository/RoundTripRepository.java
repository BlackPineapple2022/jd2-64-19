package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Favourite;
import by.academy.it.travelcompany.entity.RoundTrip;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface RoundTripRepository extends CrudRepository<RoundTrip, Serializable> {

        List<RoundTrip> findAllByFavouriteListContains(Favourite favourite);

}
