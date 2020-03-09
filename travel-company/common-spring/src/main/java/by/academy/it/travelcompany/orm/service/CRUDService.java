package by.academy.it.travelcompany.orm.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDService<E> {

    E add(E e);

    E update(E e);

    E get(Serializable id);

    void remove(Serializable id);

    List<E> findAll();

}