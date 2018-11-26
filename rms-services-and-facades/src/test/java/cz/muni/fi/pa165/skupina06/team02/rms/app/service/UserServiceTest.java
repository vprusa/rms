package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.hibernate.service.spi.ServiceException;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private User user;

    @BeforeMethod
    public void setUp() {
        user = new User(1L);
        user.setEmail("user@user.com");
        user.setFirstName("Us");
        user.setLastName("Er");
        user.setPassword("pwd");
    }

    @Test
    public void testGetAllUsers() {
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
        List<User> users = userService.getAllUsers();
        Assert.assertEquals(1, users.size());
        Assert.assertTrue(users.get(0).getId().equals(1L));
    }

    @Test
    public void testFindById() {
        when(userDao.findById(1L)).thenReturn(user);
        User users = userService.findById(1L);
        Assert.assertTrue(users.getId().equals(1L));
        Assert.assertEquals(users.getEmail(), "user@user.com");
        Assert.assertEquals(users.getFirstName(), "Us");
        Assert.assertEquals(users.getLastName(), "Er");
        Assert.assertEquals(users.getPassword(), "pwd");
    }

    @Test
    public void testFindByEmail() {
        when(userDao.findByEmail("user@user.com")).thenReturn(user);
        User users = userService.findByEmail("user@user.com");
        Assert.assertTrue(users.getId().equals(1L));
        Assert.assertEquals(users.getEmail(), "user@user.com");
        Assert.assertEquals(users.getFirstName(), "Us");
        Assert.assertEquals(users.getLastName(), "Er");
        Assert.assertEquals(users.getPassword(), "pwd");
    }
}