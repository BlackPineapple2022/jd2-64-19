package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Favourite;
import by.academy.it.travelcompany.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface FavouriteRepository extends CrudRepository<Favourite, Serializable> {

    Favourite getByUser(User user);

}
