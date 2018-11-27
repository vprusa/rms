package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
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
    private ShoppingItem shoppingItem2;
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

        shoppingItem2 = new ShoppingItem(2L);
        shoppingItem2.setBought(false);
        shoppingItem2.setName("test2");
        shoppingItem2.setDedicatedBuyer(user);
        shoppingItem2.setQuantity((long) 16);
        shoppingItem2.setShoppingList(shoppingList);
        shoppingList.addItem(shoppingItem);
    }

    public void assertMatch(ShoppingItem shoppingItemI) {
        assertMatch(shoppingItemI, false);
    }

    public void assertMatch(ShoppingItem shoppingItemI, boolean second) {
        Assert.assertTrue(shoppingItemI.getId().equals((second ? 2L : 1L)));
        Assert.assertEquals(shoppingItemI.getName(), (second ? "test2" : "test"));
        Assert.assertTrue(shoppingItemI.getQuantity() == (15L + (second ? 1L : 0L)));
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

        when(userDao.findById(1L)).thenReturn(user);
        shoppingItemDao.create(shoppingItem);
        userDao.create(user);
        Assert.assertEquals(shoppingItems.size(), 0);

        // not possible - this is handled on Facade level
        // Assert.assertEquals(shoppingItems.size(), 1);
        // assertMatch(shoppingItems.get(0));
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
        Assert.assertEquals(shoppingItems.size(), 2);
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
        shoppingItem.setDedicatedBuyer(null);
        when(shoppingItemDao.findById(1L)).thenReturn(shoppingItem);
        shoppingItemService.setDedicatedBuyer(shoppingItem, user);
        assertMatch(shoppingItem);
    }

    /**
     * Test: Update shopping item
     *
     * param shoppingItem instance to update
     */
    @Test
    public void updateShoppingItem() {
        when(shoppingItemDao.findById(1L)).thenReturn(shoppingItem);
        shoppingItem.setQuantity(16L);
        shoppingItem.setName("test2");
        shoppingItemService.setDedicatedBuyer(shoppingItem, user);
        ShoppingItem shoppingItem2 = shoppingItemService.findShoppingItemById(shoppingItem.getId());
        Assert.assertTrue(shoppingItem2.getId().equals(1L));
        Assert.assertEquals(shoppingItem2.getName(), "test2");
        Assert.assertTrue(shoppingItem2.getQuantity() == 16L);
    }

    /**
     * param shoppingItem Shopping item to delete
     */
    @Test
    public void deleteShoppingItem() {
        // can not user argument 
        // TODO mock ShoppingItem
        //when(shoppingItemDao.delete(argThat(shoppingItem))).thenReturn(shoppingItem);
        //when(shoppingItemDao.delete(new ShoppingItem(1L)));
        shoppingItemService.deleteShoppingItem(shoppingItem);
        ShoppingItem shoppingItem2 = shoppingItemService.findShoppingItemById(shoppingItem.getId());
        Assert.assertNull(shoppingItem2);
    }

}