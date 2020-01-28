package by.academy.it.travelcompany.entity;

import by.academy.it.travelcompany.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TransferTest {


    @Test
    public void saveTest(){
        Transfer transfer = new Transfer
                (null,"Train Minsk-Vilnius","Minsk railway central station","Vilnius raileay central station",
                LocalDateTime.of(2020,6,1,12,0),
                LocalDateTime.of(2020,6,1,14,0),
                39.13,"BYN");

        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transfer);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            Transfer transferFromDb = entityManager.find(Transfer.class, transfer.getId());
            Assert.assertEquals(transfer, transferFromDb);

        }catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception { HibernateUtil.close(); }

}