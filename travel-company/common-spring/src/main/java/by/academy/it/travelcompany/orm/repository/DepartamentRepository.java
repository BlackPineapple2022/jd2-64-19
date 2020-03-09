package by.academy.it.travelcompany.orm.repository;

import by.academy.it.travelcompany.orm.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface DepartamentRepository extends CrudRepository<Department, Serializable> {



}
