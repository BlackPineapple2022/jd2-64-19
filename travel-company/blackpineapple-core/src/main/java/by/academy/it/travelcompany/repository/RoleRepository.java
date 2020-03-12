package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface RoleRepository extends CrudRepository<Role, Serializable> {

    Role findByName(String name);

}
