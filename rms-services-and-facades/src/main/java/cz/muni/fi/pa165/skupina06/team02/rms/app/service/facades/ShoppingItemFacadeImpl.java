package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingItemFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingItemService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Lacko
 */
@Service
@Transactional
public class ShoppingItemFacadeImpl implements ShoppingItemFacade {
    @Autowired
    private ShoppingItemService shoppingItemService;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService mappingService;

    @Override
    public ShoppingItemDTO getItemById(Long itemId) {
        ShoppingItem item = shoppingItemService.findShoppingItemById(itemId);
        return item == null ? null : mappingService.mapTo(item, ShoppingItemDTO.class);
    }

    @Override
    public List<ShoppingItemDTO> getItemsByUser(Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return null;
        }

        List<ShoppingItem> items = shoppingItemService.getShoppingItemByUser(user);
        return items == null ? null : mappingService.mapTo(items, ShoppingItemDTO.class);
    }

    @Override
    public List<ShoppingItemDTO> getItemsByShoppingList(Long listId) {
        ShoppingList list = shoppingListService.findShoppingListById(listId);
        if (list == null) {
            return null;
        }

        List<ShoppingItem> items = shoppingItemService.getItemsFromShoppingList(list);
        return items == null ? null : mappingService.mapTo(items, ShoppingItemDTO.class);
    }

    @Override
    public List<ShoppingItemDTO> getAllItems() {
        List<ShoppingItem> items = shoppingItemService.findAllShoppingItems();
        return items == null ? null : mappingService.mapTo(items, ShoppingItemDTO.class);
    }

    @Override
    public Long createItem(ShoppingItemCreateDTO createDTO) {
        return shoppingItemService.createShoppingItem(
                mappingService.mapTo(createDTO, ShoppingItem.class)
        ).getId();
    }

    @Override
    public void buyItem(Long itemId) {
        ShoppingItem item = shoppingItemService.findShoppingItemById(itemId);
        item.setBought(true);
        shoppingItemService.updateShoppingItem(item);
    }
}
