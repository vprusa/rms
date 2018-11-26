package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListDTO;

import java.util.List;

/**
 * @author Martin Lacko
 */
public interface ShoppingListFacade {
    /**
     * Find shopping list by specified ID
     *
     * @param listId ID of shopping list
     * @return DTO with shopping list data
     */
    ShoppingListDTO getListById(Long listId);

    /**
     * Find all shopping list associated with household
     *
     * @param householdId id
     * @return List of DTOs containing all shopping lists data
     */
    List<ShoppingListDTO> getListsByHousehold(Long householdId);

    /**
     * Find all shopping lists
     *
     * @return List of DTOs with all shopping lists
     */
    List<ShoppingListDTO> getAllShoppingLists();

    /**
     * Create new shopping list
     *
     * @param createDTO DTO containing all data to create new shopping list
     * @return ID of newly created shopping list
     */
    Long createList(ShoppingListCreateDTO createDTO);
}
