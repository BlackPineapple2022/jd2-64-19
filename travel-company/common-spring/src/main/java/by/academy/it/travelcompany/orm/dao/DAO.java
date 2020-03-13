package by.academy.it.travelcompany.orm.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<E> {

    E add(E e);

    E update(E e);

    E get(Serializable id);

    void delete(Serializable id);

    List<E> findAll();
}

