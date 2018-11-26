package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
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
    private HouseholdDao householdListDao;
    
    @InjectMocks
    private ShoppingItemService shoppingItemService = new ShoppingItemServiceImpl();

    @InjectMocks
    private ShoppingListService shoppingListService = new ShoppingListServiceImpl();

    @InjectMocks
    private HouseholdService householdService = new HouseholdServiceImpl();
    
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ShoppingItem shoppingItem;
    private ShoppingList shoppingList;
    private Household household;
    private User user;

    @BeforeMethod
    public void setUp() {
        user = new User(1L);
        user.setEmail("user@user.com");
        user.setFirstName("Us");
        user.setLastName("Er");
        user.setPassword("pwd");
        
        household = new Household(1L);
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
        shoppingList.setHousehold(household);
    }

    public void assertMatch(ShoppingItem shoppingItemI) {
        Assert.assertTrue(shoppingItemI.getId().equals(1L));
        Assert.assertEquals(shoppingItemI.getName(), "test");
        Assert.assertTrue(shoppingItemI.getQuantity() == 15L);
        Assert.assertFalse(shoppingItemI.getBought());
        Assert.assertEquals(shoppingItemI.getDedicatedBuyer(), user);
        Assert.assertEquals(shoppingItemI.getShoppingList(), shoppingList);
    }

    /**
     * Test: Find shopping item by its ID
     *
     * param id ID of the shopping item to find return item with matching ID, null
     * if such item does not exists
     */
    @Test
    public void findShoppingItemById() {
        when(shoppingItemDao.findById(1L)).thenReturn(shoppingItem);
        ShoppingItem shoppingItem2 = shoppingItemService.findShoppingItemById(1L);
        assertMatch(shoppingItem2);
    }

    /**
     * Test: Find shopping items by dedicated buyer
     *
     * param user dedicated buyer return list of items which should dedicated buyer
     * buy
     */
    @Test
    public void getShoppingItemByUser() {
        when(shoppingItemDao.findById(1L)).thenReturn(shoppingItem);
        when(userDao.findById(1L)).thenReturn(user);
        List<ShoppingItem> shoppingItems = shoppingItemService.getShoppingItemByUser(user);
        // No users
        Assert.assertEquals(shoppingItems.size(), 0);
        //assertMatch(shoppingItems.get(0));
    }

    /**
     * Test: Find all items on shopping list
     *
     * param shoppingList shopping list with items return list of item on given
     * shopping list
     */
    @Test
    public void getItemsFromShoppingList() {
        when(shoppingItemDao.findById(1L)).thenReturn(shoppingItem);
        when(shoppingListDao.findById(1L)).thenReturn(shoppingList);
        when(userDao.findById(1L)).thenReturn(user);
        List<ShoppingItem> shoppingItems = shoppingItemService.getItemsFromShoppingList(shoppingList);
        Assert.assertEquals(shoppingItems.size(), 1);
        assertMatch(shoppingItems.get(0));
    }

    /**
     * Test: return list of all item in database
     */
    @Test
    public void findAllShoppingItems() {
        when(shoppingItemDao.findAll()).thenReturn(Collections.singletonList(shoppingItem));
        List<ShoppingItem> shoppingItems = shoppingItemService.findAllShoppingItems();
        Assert.assertEquals(shoppingItems.size(), 1);
        assertMatch(shoppingItems.get(0));
    }

    /**
     * Test: Set buyer for and shopping item
     *
     * param shoppingItem Shopping item to set the buyer param user dedicated buyer
     * for item
     */
    @Test
    public void setDedicatedBuyer() {
            
    }

    /**
     * Test: Update shopping item
     *
     * param shoppingItem instance to update
     */
    @Test
    public void updateShoppingItem() {
      
    }

    /**
     * param shoppingItem Shopping item to delete
     */
    public void deleteShoppingItem() {

    }

    /**
     * Test: Add item to the specific shopping list
     * <p>
     * If shopping item has already set the shopping list, InvalidArgumentException
     * is thrown.
     */
    @Test
    public void addItemToShoppingList() {

    }

    /**
     * Test: Move item to other shopping list
     * <p>
     * Moves item to list, without checking, if list is not already set.
     *
     * param shoppingItem item to move param shoppingList shoppinglist to move the
     * item
     */
    @Test
    public void moveItemToShoppingList() {

    }
}