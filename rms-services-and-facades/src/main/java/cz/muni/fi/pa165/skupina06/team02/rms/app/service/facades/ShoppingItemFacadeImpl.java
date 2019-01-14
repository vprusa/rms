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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final public static Logger logger = LoggerFactory.getLogger(ShoppingItemFacadeImpl.class);
    
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
        ShoppingItem itemDTO = mappingService.mapTo(createDTO, ShoppingItem.class);
        itemDTO.setShoppingList(shoppingListService.findShoppingListById(createDTO.getShoppingListId()));
        itemDTO.setDedicatedBuyer(userService.findById(createDTO.getBuyerId()));
        return shoppingItemService.createShoppingItem(
                itemDTO
        ).getId();
    }

    @Override
    public void buyItem(Long itemId) {
        ShoppingItem item = shoppingItemService.findShoppingItemById(itemId);
        item.setBought(true);
        shoppingItemService.updateShoppingItem(item);
    }
}
