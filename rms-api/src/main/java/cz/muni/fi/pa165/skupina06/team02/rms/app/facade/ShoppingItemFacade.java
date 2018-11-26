package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;

import java.util.List;

/**
 * @author Martin Lacko
 */
public interface ShoppingItemFacade {
    /**
     * Find Shopping item by specific ID
     *
     * @param itemId ID of item to find
     * @return DTO containing shopping item data
     */
    ShoppingItemDTO getItemById(Long itemId);

    /**
     * Find all items, associated with given user
     *
     * @param userId ID of user to find associated items
     * @return List of DTOs containing shopping item(s) data
     */
    List<ShoppingItemDTO> getItemsByUser(Long userId);

    /**
     * Find all items on shopping list
     *
     * @param listId ID of shopping list to look up items
     * @return List of DTOs of all items on shopping list
     */
    List<ShoppingItemDTO> getItemsByShoppingList(Long listId);

    /**
     * Find all shopping items
     *
     * @return List of DTOs with all shopping items
     */
    List<ShoppingItemDTO> getAllItems();

    /**
     * Create new shopping item
     *
     * @param createDTO DTO with all shopping item data
     * @return ID of newly created shopping item.
     */
    Long createItem(ShoppingItemCreateDTO createDTO);

    /**
     * Set shopping item as bought
     *
     * @param itemId ID of item marked as bought
     */
    void buyItem(Long itemId);
}
