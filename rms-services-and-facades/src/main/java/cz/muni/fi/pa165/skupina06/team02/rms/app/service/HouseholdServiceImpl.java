package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 * 
 *         Implementation of the {@link HouseholdService}. This class is part of
 *         the service module of the application that provides the
 *         implementation of the business logic (main logic of the application).
 * 
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private ShoppingListDao shoppingListDao;

    @Autowired
    private HouseholdDao householdDao;

    @Autowired
    private UserDao userDao;

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * createHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void createHousehold(Household household) {
        householdDao.create(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * findHouseholdById(java.lang.Long)
     */
    @Override
    public Household findHouseholdById(Long id) {
        return householdDao.findById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * findAllHouseholds()
     */
    @Override
    public List<Household> findAllHouseholds() {
        return householdDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * getHouseholdsByUser(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User)
     */
    @Override
    public List<Household> getHouseholdsByUser(User user) {
        // user.getHouseholds()
        // return householdDao.findByUser(user);
        // TODO more db queies
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * getHouseholdsByShoppingList(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.
     * ShoppingList)
     */
    @Override
    public List<ShoppingList> getHouseholdsByShoppingList(ShoppingList shoppingList) {
        // TODO idk if like this of to pass it through transactions?
        return shoppingList.getHousehold().getShoppingLists();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * updateHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void updateHousehold(Household household) {
        householdDao.update(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * deleteHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void deleteHousehold(Household household) {
        householdDao.delete(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * removeTenant(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void removeTenant(User tenant, Household household) {
        tenant.getHouseholds();
        // do the magic here with household lists and check i manually or better via db
        // query?
        // householdDao.
        // userDao.
        // TODO check out addTenant(...), any missing method?
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#addTenant(
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void addTenant(User tenant, Household household) {
        // TODO idk like this?
        tenant.joinHousehold(household);
        household.addTenant(tenant);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * addToShoppingLists(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.
     * ShoppingList, cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void addToShoppingLists(ShoppingList shoppingList, Household household) {
        // TODO check please
        shoppingList.setHousehold(household);
        household.addToShoppingLists(shoppingList);
    }

}
