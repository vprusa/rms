package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;

/**
 * @author Vojtech Prusa
 *
 */
public interface ShoppingListDao {

    /**
     * Find ShoppingList by id
     * 
     * @param id
     * @return
     */
    public ShoppingListDao findById(Long id);

    /**
     * Create Shopping list
     * 
     * @param ShoppingList instance
     */
    public void create(ShoppingList sl);

    /**
     * @param ShoppingList instance
     */
    public void delete(ShoppingList sl);

    /**
     * @return List of all ShoppingLists
     */
    public List<ShoppingList> findAll();

    /**
     * Find ShoppingList by its name
     * 
     * @param name
     * @return ShoppingList instance
     */
    public ShoppingListDao findByName(String name);
}
