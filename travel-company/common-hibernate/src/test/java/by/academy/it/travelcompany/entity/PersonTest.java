package by.academy.it.travelcompany.entity;

import by.academy.it.travelcompany.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void saveTest() {
        Person person = new Person(null, 33, "Alexander", "Zenkevich");
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            Person personFromDb = entityManager.find(Person.class, person.getId());
            Assert.assertEquals(person, personFromDb);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
    @AfterClass
    public static void afterClass() throws Exception { HibernateUtil.close(); }

}