
package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractDAO<E> implements DAO<E> {

    private final Class<E> clazz;
    ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private final String GET_ALL;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    protected AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
        this.GET_ALL = "FROM " + clazz.getSimpleName();
    }

    @Override
    public E get(Serializable id) {
        E e = getEm().find(clazz, id);
        return e;
    }

    @Override
    public E add(E e) {

        begin();
        getEm().persist(e);
        commit();
        return e;

    }

    @Override
    public E update(E e) {
       begin();
       getEm().merge(e);
       commit();
       return e;
    }

    @Override
    public void delete(Serializable id) {
        begin();
        E e = get (id);
        getEm().remove(e);
        commit();
    }

    @Override
    public List<E> findAll() {
        begin();
        List<E> es =  getEm().createQuery(GET_ALL,clazz).getResultList();
        commit();
        return es;
    }

    public EntityManager getEm() {

        if (em.get() == null) {
            em.set(entityManagerFactory.createEntityManager());
        }
        return em.get();

    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }


}
