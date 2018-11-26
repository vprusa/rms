package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;


/**
 * @author Vojtech Prusa
 *
 * Household Facade
 *
 */
@Service
@Transactional
public class HouseholdFacadeImpl implements HouseholdFacade {
    @Autowired
    private HouseholdService householdService;

    @Autowired
    private BeanMappingService beanMappingService;

    /* (non-Javadoc)
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#getHouseholdById(java.lang.Long)
     */
    @Override
    public HouseholdDTO getHouseholdById(Long id) {
        Household household = householdService.findHouseholdById(id);
        return (household == null) ? null : beanMappingService.mapTo(household, HouseholdDTO.class);
    }

    /* (non-Javadoc)
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#createHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO)
     */
    @Override
    public Long createHousehold(HouseholdDTO householdDTO) {
        Household household = new Household();
        // household.setName(householdDTO.getName());
        householdService.createHousehold(household);
        return household.getId();
    }

    /* (non-Javadoc)
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade#getAllHouseholds()
     */
    @Override
    public List<HouseholdDTO> getAllHouseholds() {
        return beanMappingService.mapTo(householdService.findAll(), HouseholdDTO.class);
    }
}
