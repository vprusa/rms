package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ShopingListServiceTest {
    @Mock
    private ShoppingItemDao shoppingItemDao;

    @Mock
    private UserDao userDao;

    @Mock
    private ShoppingListDao shoppingListDao;

    @Mock
    private ShoppingListDao shoppingListListDao;
    
    @Mock
    private HouseholdDao householdDao;
    
    @InjectMocks
    private ShoppingItemService shoppingItemService = new ShoppingItemServiceImpl();

    @InjectMocks
    private HouseholdService householdService = new HouseholdServiceImpl();

    @InjectMocks
    private ShoppingListService shoppingListService = new ShoppingListServiceImpl();
    
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ShoppingItem shoppingItem;
    private Household household;
    private ShoppingList shoppingList;
    private User user;

    @BeforeMethod
    public void setUp() {
        user = new User(1L);
        user.setEmail("user@user.com");
        user.setFirstName("Us");
        user.setLastName("Er");
        user.setPassword("pwd");
        
        household = new Household(1L);
        
        shoppingList = new ShoppingList(1L);
        shoppingList.setName("test");

        shoppingItem = new ShoppingItem(1L);
        shoppingItem.setBought(false);
        shoppingItem.setName("test");
        shoppingItem.setDedicatedBuyer(user);
        shoppingItem.setQuantity((long) 15);
        shoppingItem.setShoppingList(shoppingList);
        shoppingList.setHousehold(household);
        shoppingList.addItem(shoppingItem);
    }

    public void assertMatch(ShoppingList shoppingListI) {
        Assert.assertTrue(shoppingListI.getId().equals(1L));
        Assert.assertEquals(shoppingListI.getName(), "test");
        Assert.assertEquals(shoppingListI.getShoppingItems(), Arrays.asList(shoppingItem));
    }

    /**
     * Test: Find shopping list by its ID
     *
     * param id ID of the shopping item to find return item with matching ID, null
     * if such item does not exists
     */
    @Test
    public void findShoppingListItemById() {
        when(shoppingListDao.findById(1L)).thenReturn(shoppingList);
        ShoppingList shoppingListI = shoppingListService.findShoppingListById(1L);
        assertMatch(shoppingListI);
    }

    /**
     * Test: Find shopping lsits by name
     */
    @Test
    public void getShoppingListByName() {
        when(shoppingListDao.findByName("test")).thenReturn(Arrays.asList(shoppingList));
        List<ShoppingList> shoppingLists = shoppingListService.getShoppingListsByName("test");
        Assert.assertNotNull(shoppingLists);
        Assert.assertEquals(shoppingLists.size(), 1);
        assertMatch(shoppingLists.get(0));
    }

    /**
     * Test: Get shoppinglist by household
     */
    @Test
    public void getShoppingListForHosuheold() {
        when(shoppingListDao.findById(1L)).thenReturn(shoppingList);
        when(userDao.findById(1L)).thenReturn(user);
        when(householdDao.findById(1L)).thenReturn(household);
        // TODO mock objects
        //List<ShoppingList> shoppingLists = shoppingListService.getShoppingListsForHousehold(household);
        //Assert.assertNotNull(shoppingLists);
        //Assert.assertEquals(shoppingLists.size(), 1);
        //assertMatch(shoppingLists.get(0));
    }

}