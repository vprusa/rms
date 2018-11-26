package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
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
public class ShopingItemServiceTest {
    @Mock
    private ShoppingItemDao userDao;

    @InjectMocks
    private ShoppingItemService shoppingItemService = new ShoppingItemServiceImpl();
    
    @InjectMocks
    private ShoppingListService shoppingListService = new ShoppingItemServiceImpl();

    @InjectMocks
    private UserService userService = new ShoppingItemServiceImpl();


    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ShoppingItem shoppingItem;

    @BeforeMethod
    public void setUp() {
        shoppingItem = new ShoppingItem(1L);
        shoppingItem.setBought(false);
        shoppingItem.setset
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
    
    /**
     * Test: Create new shopping item
     *
     * param item Item to create
     */
    void createShoppingItem(ShoppingItem item){
        
    }

    /**
     *Test: Find shopping item by its ID
     *
     * param id ID of the shopping item to find
     * return item with matching ID, null if such item does not exists
     */
    void findShoppingItemById(Long id){
        
    }

    /**
     * Test: Find shopping items by dedicated buyer
     *
     * param user dedicated buyer
     * return list of items which should dedicated buyer buy
     */
    void getShoppingItemByUser(User user){
        
    }

    /**
     * Test: Find all items on shopping list
     *
     * param shoppingList shopping list with items
     * return list of item on given shopping list
     */
    void getItemsFromShoppingList(ShoppingList shoppingList){
        
    }

    /**
     * Test: 
     * return list of all item in database
     */
    void findAllShoppingItems(){
        
    }

    /**
     * Test: Set buyer for and shopping item
     *
     * param shoppingItem Shopping item to set the buyer
     * param user         dedicated buyer for item
     */
    void setDedicatedBuyer(ShoppingItem shoppingItem, User user){
        
    }

    /**
     * Test: Update shopping item
     *
     * param shoppingItem instance to update
     */
    void updateShoppingItem(ShoppingItem shoppingItem){
        
    }

    /**
     * param shoppingItem Shopping item to delete
     */
    void deleteShoppingItem(ShoppingItem shoppingItem){
        
    }

    /**
     * Test:  Add item to the specific shopping list
     * <p>
     * If shopping item has already set the shopping list, InvalidArgumentException is thrown.
     */
    void addItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList){
        
    }

    /**
     * Test: Move item to other shopping list
     * <p>
     * Moves item to list, without checking, if list is not already set.
     *
     * param shoppingItem item to move
     * param shoppingList shoppinglist to move the item
     */
    void moveItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList){
        
    }
}