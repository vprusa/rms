package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;

/**
 * @author Vojtech Prusa
 *
 *         Household Facade
 *
 */
@Service
@Transactional
public class HouseholdFacadeImpl implements HouseholdFacade {
    @Autowired
    private HouseholdService householdService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * getHouseholdById(java.lang.Long)
     */
    @Override
    public HouseholdDTO findHouseholdById(Long id) {
        Household household = householdService.findHouseholdById(id);
        return (household == null) ? null : beanMappingService.mapTo(household, HouseholdDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * createHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public Long createHousehold(HouseholdDTO householdDTO) {
        Household household = new Household();
        // household.setName(householdDTO.getName());
        householdService.createHousehold(household);
        return household.getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * getAllHouseholds()
     */
    @Override
    public List<HouseholdDTO> findAll() {
        return beanMappingService.mapTo(householdService.findAll(), HouseholdDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * getHouseholdsByUser(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO)
     */
    /*
     * @Override public List<HouseholdDTO> getHouseholdsByUser(UserDTO user) {
     * List<Household> houseolds =
     * householdService.getHouseholdsByUser(beanMappingService.mapTo(user,
     * User.class));- return beanMappingService.mapTo(houseolds,
     * HouseholdDTO.class); }
     */

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * updateHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void updateHousehold(HouseholdDTO household) {
        householdService.updateHousehold(beanMappingService.mapTo(household, Household.class));
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#
     * deleteHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void deleteHousehold(HouseholdDTO household) {
        householdService.deleteHousehold(beanMappingService.mapTo(household, Household.class));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#removeTenant
     * (cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void removeTenant(UserDTO tenant, HouseholdDTO household) {
        householdService.removeTenant(tenant.getId(), household.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#addTenant(cz
     * .muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public void addTenant(UserDTO tenant, HouseholdDTO household) {
        householdService.addTenant(tenant.getId(), household.getId());
    }
}
