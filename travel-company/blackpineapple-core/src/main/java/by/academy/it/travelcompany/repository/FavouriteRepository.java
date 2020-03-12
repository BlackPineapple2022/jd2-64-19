package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Favourite;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface FavouriteRepository extends CrudRepository<Favourite, Serializable> {
}
