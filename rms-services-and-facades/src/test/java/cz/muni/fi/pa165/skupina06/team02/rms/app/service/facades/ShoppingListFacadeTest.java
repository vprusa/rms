package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingItemService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingItemServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserServiceImpl;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class ShoppingListFacadeTest {

    @Mock
    private ShoppingItemDao shoppingItemDao;

    @Mock
    private UserDao userDao;

    @Mock
    private ShoppingListDao shoppingListDao;

    @InjectMocks
    private ShoppingItemService shoppingItemService = new ShoppingItemServiceImpl();

    @InjectMocks
    private ShoppingListService shoppingListService = new ShoppingListServiceImpl();

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ShoppingItem shoppingItem;
    private ShoppingList shoppingList;
    private User user;

    @BeforeMethod
    public void setUp() {
        user = new User(1L);
        user.setEmail("user@user.com");
        user.setFirstName("Us");
        user.setLastName("Er");
        user.setPassword("pwd");

        shoppingList = new ShoppingList(1L);
        shoppingList.setName("ShoppingListTest");

        shoppingItem = new ShoppingItem(1L);
        shoppingItem.setBought(false);
        shoppingItem.setName("test");
        shoppingItem.setDedicatedBuyer(user);
        shoppingItem.setQuantity((long) 15);
        shoppingItem.setShoppingList(shoppingList);
        shoppingList.addItem(shoppingItem);
    }

    public void assertMatch(ShoppingList shoppingListI) {
        Assert.assertTrue(shoppingListI.getId().equals(1L));
        Assert.assertEquals(shoppingListI.getName(), "ShoppingListTest");

    }
    
    @Test
    public void testShoppingItemFacade() {
        when(shoppingListService.findShoppingListById(1L)).thenReturn(shoppingList);
        ShoppingList shoppingList2 = shoppingListService.findShoppingListById(1L);
        assertMatch(shoppingList2);
    }
}