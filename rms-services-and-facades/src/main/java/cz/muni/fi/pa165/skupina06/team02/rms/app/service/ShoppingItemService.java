package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

import java.util.List;

/**
 * @author Martin Lacko
 */
public interface ShoppingItemService {
    /**
     * Create new shopping item
     *
     * @param item Item to create
     */
    ShoppingItem createShoppingItem(ShoppingItem item);

    /**
     * Find shopping item by its ID
     *
     * @param id ID of the shopping item to find
     * @return item with matching ID, null if such item does not exists
     */
    ShoppingItem findShoppingItemById(Long id);

    /**
     * Find shopping items by dedicated buyer
     *
     * @param user dedicated buyer
     * @return list of items which should dedicated buyer buy
     */
    List<ShoppingItem> getShoppingItemByUser(User user);

    /**
     * Find all items on shopping list
     *
     * @param shoppingList shopping list with items
     * @return list of item on given shopping list
     */
    List<ShoppingItem> getItemsFromShoppingList(ShoppingList shoppingList);

    /**
     * @return list of all item in database
     */
    List<ShoppingItem> findAllShoppingItems();

    /**
     * Set buyer for and shopping item
     *
     * @param shoppingItem Shopping item to set the buyer
     * @param user         dedicated buyer for item
     */
    void setDedicatedBuyer(ShoppingItem shoppingItem, User user);

    /**
     * Update shopping item
     *
     * @param shoppingItem instance to update
     */
    void updateShoppingItem(ShoppingItem shoppingItem);

    /**
     * @param shoppingItem Shopping item to delete
     */
    void deleteShoppingItem(ShoppingItem shoppingItem);

    /**
     * Add item to the specific shopping list
     * <p>
     * If shopping item has already set the shopping list, InvalidArgumentException is thrown.
     *
     * @param shoppingItem item to add to the shopping list
     * @param shoppingList shopping list to add the item
     */
    void addItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList);

    /**
     * Move item to other shopping list
     * <p>
     * Moves item to list, without checking, if list is not already set.
     *
     * @param shoppingItem item to move
     * @param shoppingList shoppinglist to move the item
     */
    void moveItemToShoppingList(ShoppingItem shoppingItem, ShoppingList shoppingList);
}
