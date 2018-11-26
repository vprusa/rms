package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;

/**
 * @author Vojtech Prusa
 *
 *  Facade interface for Household 
 *
 */
public interface HouseholdFacade
{
    /**
     * Returns list of all Households DTOs
     * 
     * @return list of household DTOs
     */
    List<HouseholdDTO> getAllHouseholds();
    
    /**
     * Get household DTO
     * 
     * @param id to find by
     * @return household DTO instance
     */
    HouseholdDTO getHouseholdById(Long id);

    /**
     * Create Household DTO
     * 
     * @param householdDTO instance
     * @return id value
     */
    Long createHousehold(HouseholdDTO householdDTO);
}
