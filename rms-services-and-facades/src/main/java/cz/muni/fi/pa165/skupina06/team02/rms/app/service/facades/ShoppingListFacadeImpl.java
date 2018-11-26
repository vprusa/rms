package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingListDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingListFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingListFacadeImpl implements ShoppingListFacade {
    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private BeanMappingService mappingService;

    @Autowired
    private HouseholdService householdService;

    @Override
    public ShoppingListDTO getListById(Long listId) {
        ShoppingList list = shoppingListService.findShoppingListById(listId);
        return list == null ? null : mappingService.mapTo(list, ShoppingListDTO.class);
    }

    @Override
    public List<ShoppingListDTO> getListsByHousehold(Long householdId) {
        Household household = householdService.findHouseholdById(householdId);
        if (household == null) {
            return null;
        }

        List<ShoppingList> lists = shoppingListService.getShoppingListsForHousehold(household);
        return lists == null ? null : mappingService.mapTo(lists, ShoppingListDTO.class);
    }

    @Override
    public List<ShoppingListDTO> getAllShoppingLists() {
        List<ShoppingList> lists = shoppingListService.findAllShoppingLists();
        return lists == null ? null : mappingService.mapTo(lists, ShoppingListDTO.class);
    }

    @Override
    public Long createList(ShoppingListCreateDTO createDTO) {
        return shoppingListService.createShoppingList(
                mappingService.mapTo(createDTO, ShoppingList.class)
        ).getId();
    }
}
