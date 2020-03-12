package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface UserRepository extends CrudRepository<User, Serializable> {

}
