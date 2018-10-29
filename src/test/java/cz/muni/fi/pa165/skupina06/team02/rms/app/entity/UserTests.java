package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class UserTests extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * Test class for User entity
     */
    @Test
    public void basicTransactionTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail");
        //user.setEmail("TestPassword");
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        //EntityManager em2 = emf.createEntityManager();
        //em2.getTransaction().begin();
        //User user2 = em2.find(User.class, user.getId());
//        assertTrue("User ID mismatch - user1: " + user.getId() + " does not match user2: " + user2.getId(), user.getId().equals(user2.getId()));
//        assertTrue(" mismatch - user1: " + user.get + " does not match user2: " + user2.get,user.get.equals(user2.get));
//        assertTrue("Firstname mismatch - user1: " + user.getFirstName() + " does not match user2: " + user2.getFirstName(), user.getFirstName().equals(user2.getFirstName()));
//        assertTrue("Lastname mismatch - user1: " + user.getLastName() + " does not match user2: " + user2.getLastName(), user.getLastName().equals(user2.getLastName()));
//        assertTrue("Password mismatch - user1: " + user.getPassword() + " does not match user2: " + user2.getPassword(), user.getPassword().equals(user2.getPassword()));

    }

}
