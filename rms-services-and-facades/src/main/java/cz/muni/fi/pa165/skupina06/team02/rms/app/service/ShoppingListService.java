package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;

import java.util.List;

/**
 * @author Martin Lacko
 */
public interface ShoppingListService {
    /**
     * Create new shopping list
     *
     * @param shoppingList list to create
     */
    ShoppingList createShoppingList(ShoppingList shoppingList);

    /**
     * Find shopping list by its ID
     *
     * @param id ID of shopping list to find
     * @return Shopping list with matching ID, null if such list does not exists
     */
    ShoppingList findShoppingListById(Long id);

    /**
     * Find all shopping lists for given household
     *
     * @param household household to search shopping lists
     * @return list of shopping lists
     */
    List<ShoppingList> getShoppingListsForHousehold(Household household);

    /**
     * Find all shopping list by given name
     *
     * @param name name of list
     * @return list of shopping lists with mathing name
     */
    List<ShoppingList> getShoppingListsByName(String name);

    /**
     * Find all shopping lists in database
     *
     * @return list of shopping lists
     */
    List<ShoppingList> findAllShoppingLists();

    /**
     * Add given shopping list to household.
     * <p>
     * If shopping list has already set the household, InvalidArgumentException is thrown.
     *
     * @param shoppingList specified shopping list to add to household
     * @param household    household to add the shopping list
     */
    void addShoppingListToHousehold(ShoppingList shoppingList, Household household);

    /**
     * Move given shopping list to household.
     * <p>
     * Move list to household, without checking if the household is set already.
     *
     * @param shoppingList specified shopping list to add to household
     * @param household    household to add the shopping list
     */
    void moveShoppingListToHousehold(ShoppingList shoppingList, Household household);
}
