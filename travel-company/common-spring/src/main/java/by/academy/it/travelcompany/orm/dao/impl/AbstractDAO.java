/*
package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

public abstract class AbstractDAO<E> implements DAO<E> {

    private final Class<E> clazz;
    ThreadLocal<EntityManager> em = new ThreadLocal<>();

    //@Autowired
    */
/*private EntityManagerFactory entityManagerFactory;*//*


    protected AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E get(Serializable id) {
        return null;
        //return getEm().find(clazz, id);
    }

    @Override
    public E add(E e) {
        */
/*begin();
        getEm().persist(e);
        commit();
        return e;*//*

        return null;
    }

    @Override
    public E update(E e) {
        return null;
    }

    @Override
    public void delete(Serializable id) {

    }

    public EntityManager getEm() {
        */
/*if (em.get() == null) {
            em.set(entityManagerFactory.createEntityManager());
        }
        return em.get();*//*

        return null;
    }

   */
/* public void begin() {
        getEm().getTransaction().begin();
    }*//*


    */
/*public void commit() {
        getEm().getTransaction().commit();
    }*//*


}
*/
