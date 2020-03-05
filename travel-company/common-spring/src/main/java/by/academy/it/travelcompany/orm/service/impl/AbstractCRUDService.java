package by.academy.it.travelcompany.orm.service.impl;

import by.academy.it.travelcompany.orm.dao.DAO;
import by.academy.it.travelcompany.orm.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Service
public abstract class AbstractCRUDService<E> implements CRUDService<E> {

    private final Class<E> clazz;

    @Autowired
    private DAO<E> abstractDAO;

    protected AbstractCRUDService(Class<E> clazz){
        this.clazz = clazz;
    }

    @Override
    public E add(E e) {
        return abstractDAO.add(e);
    }

    @Override
    public E update(E e) {
        return abstractDAO.update(e);
    }

    @Override
    public E get(Serializable id) {
        return abstractDAO.get(id);
    }

    @Override
    public void remove(Serializable id) {
        abstractDAO.delete(id);
    }

}
