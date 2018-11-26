package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;

import java.util.List;

public interface ShoppingItemFacade {
    ShoppingItemDTO getItemById(Long itemId);

    List<ShoppingItemDTO> getItemsByUser(Long userId);

    List<ShoppingItemDTO> getItemsByShoppingList(Long listId);

    List<ShoppingItemDTO> getAllItems();

    Long createItem(ShoppingItemCreateDTO createDTO);

    void buyItem(Long itemId);
}
