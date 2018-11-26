package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingItemDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingListServiceImpl implements ShoppingListService {
    @Autowired
    ShoppingListDao shoppingListDao;

    @Autowired
    ShoppingItemDao shoppingItemDao;

    @Autowired
    HouseholdDao householdDao;

    @Override
    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        List<ShoppingItem> list = shoppingList.getShoppingItems();
        if (list != null) {
            for (ShoppingItem item : list) {
                shoppingItemDao.create(item);
            }
        }

        shoppingListDao.create(shoppingList);
        return shoppingList;
    }

    @Override
    public ShoppingList findShoppingListById(Long id) {
        return shoppingListDao.findById(id);
    }

    @Override
    public List<ShoppingList> getShoppingListsForHousehold(Household household) {
        return household.getShoppingLists();
    }

    @Override
    public List<ShoppingList> getShoppingListsByName(String name) {
        return shoppingListDao.findByName(name);
    }

    @Override
    public List<ShoppingList> findAllShoppingLists() {
        return shoppingListDao.findAll();
    }

    @Override
    public void addShoppingListToHousehold(ShoppingList shoppingList, Household household) {
        if (shoppingList.getHousehold() != null) {
            throw new IllegalArgumentException(String.format(
                    "Shopping list %d has already set household", household.getId()
            ));
        }
        moveShoppingListToHousehold(shoppingList, household);
    }

    @Override
    public void moveShoppingListToHousehold(ShoppingList shoppingList, Household household) {
        shoppingList.setHousehold(household);
        household.addToShoppingLists(shoppingList);
    }
}
