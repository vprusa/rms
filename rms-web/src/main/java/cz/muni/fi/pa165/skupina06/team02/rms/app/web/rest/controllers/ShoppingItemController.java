package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemCreateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingItemFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for ShoppingItem
 * 
 * @author Vojtech Prusa
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SHOPPINGITEMS)
public class ShoppingItemController extends BaseController {

    final public static Logger logger = LoggerFactory.getLogger(ShoppingItemController.class);

    @Inject
    private ShoppingItemFacade shoppingItemFacade;

    /**
     * 
     * Create new shoppingItem and returns its id
     * 
     * @param ShoppingItemCreateDTO as body
     * @return long id
     * @throws Exception ResourceNotFoundException if empty body
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST /* , produces = MediaType.APPLICATION_JSON_VALUE */, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final long createShoppingItem(@RequestBody ShoppingItemCreateDTO shoppingItem) throws Exception {
        if (shoppingItem == null) {
            throw new ResourceNotFoundException();
        }
        logger.debug("rest createShoppingItem({})", shoppingItem.toString());
        // ShoppingItemDTO shoppingItemDTO =
        // shoppingItemFacade.getItemById(shoppingItem.getI());
        long newItemId = shoppingItemFacade.createItem(shoppingItem);
        return newItemId;
    }

    /**
     * get all the categories
     * 
     * @return list of ShoppingItemDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ShoppingItemDTO> getShoppingItem() {

        logger.debug("rest getShoppingItem()");
        return shoppingItemFacade.getAllItems();
    }

    /**
     * 
     * Get one shoppingItem specified by id
     * 
     * @param id identifier for the shoppingItem
     * @return ShoppingItemDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ShoppingItemDTO getShoppingItem(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getShoppingItem({})", id);

        ShoppingItemDTO shoppingItemDTO = shoppingItemFacade.getItemById(id);
        if (shoppingItemDTO == null) {
            throw new ResourceNotFoundException();
        }

        return shoppingItemDTO;
    }

    /**
     * 
     * Buy one shoppingItem specified by id
     * 
     * @param itemId identifier for the shoppingItem
     * @return boolean if already bought false if not true
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/buy/{itemId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean buyShoppingItem(@PathVariable("itemId") long itemId) throws Exception {

        logger.info("rest getShoppingItem({})", itemId);

        ShoppingItemDTO shoppingItemDTO = shoppingItemFacade.getItemById(itemId);
        if (shoppingItemDTO == null) {
            throw new ResourceNotFoundException();
        }
        logger.info(shoppingItemDTO.toString());

        // basically null = false ..
        if (shoppingItemDTO.getBought() != null || (shoppingItemDTO.getBought() != null && shoppingItemDTO.getBought().booleanValue() == false)) {
            return false;
        }
        shoppingItemFacade.buyItem(shoppingItemDTO.getId());
        return true;
    }

}