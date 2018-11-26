package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Martin Lacko
 */
public class ShoppingItemServiceImpl implements ShoppingItemService {
    @Autowired
    private ShoppingItemDao shoppingItemDao;

    @Autowired
    private ShoppingListDao shoppingListDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ShoppingItem createShoppingItem(ShoppingItem item) {
        ShoppingList list = item.getShoppingList();
        if (list != null) {
            list.addItem(item);
        }
        shoppingItemDao.create(item);
        return item;
    }

    @Override
    public ShoppingItem findShoppingItemById(Long id) {
        return shoppingItemDao.findById(id);
    }

    @Override
    public List<ShoppingItem> getShoppingItemByUser(User user) {
        User u = userDao.findById(user.getId());
        if (u != null) {
            return u.getItems();
        } else {
            return null;
        }
    }

    @Override
    public List<ShoppingItem> getItemsFromShoppingList(ShoppingList shoppingList) {
        ShoppingList list = shoppingListDao.findById(shoppingList.getId());

        if (list != null) {
            return list.getShoppingItems();
        } else {
            return null;
        }
    }

    @Override
    public List<ShoppingItem> findAllShoppingItems() {
        return shoppingItemDao.findAll();
    }

    @Override
    public void setDedicatedBuyer(ShoppingItem shoppingItem, User user) {
        shoppingItem.setDedicatedBuyer(user);
    }

    @Override
    public void updateShoppingItem(ShoppingItem shoppingItem) {
        shoppingItemDao.update(shoppingItem);
    }

    @Override
    public void deleteShoppingItem(ShoppingItem shoppingItem) {
        shoppingItemDao.delete(shoppingItem);
    }

    @Override
    public void addItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList) {
        if (shoppingItem.getShoppingList() != null) {
            throw new IllegalArgumentException(String.format(
                    "Shopping Item %d is already in shopping list", shoppingItem.getId())
            );
        }
        moveItemToShoppingList(shoppingItem, shoppingList);
    }

    @Override
    public void moveItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList) {
        shoppingItem.setShoppingList(shoppingList);
        shoppingList.addItem(shoppingItem);
    }
}
