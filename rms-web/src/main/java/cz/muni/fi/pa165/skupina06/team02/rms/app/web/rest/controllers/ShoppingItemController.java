package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.ShoppingItemFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.exceptions.ResourceNotFoundException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for ShoppingItem
 * 
 * @author brossi
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CATEGORIES)
public class ShoppingItemController {

    final static Logger logger = LoggerFactory.getLogger(ShoppingItemController.class);

    @Inject
    private ShoppingItemFacade shoppingItemFacade;

    /**
     * get all the categories
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
}