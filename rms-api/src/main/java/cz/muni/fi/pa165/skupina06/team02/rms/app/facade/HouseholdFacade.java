package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;

/**
 * @author Vojtech Prusa
 *
 *  Facade interface for HouseholdDTO
 *
 */
public interface HouseholdFacade
{

    /**
     * Create HouseholdDTO DTO
     *
     * @param householdDTO instance
     * @return id value
     */
    Long createHousehold(HouseholdDTO householdDTO);

    /**
     * Return households by user
     *
     * @param user
     * @return list of households
     */
    //List<HouseholdDTO> getHouseholdsByUser(UserDTO user);

    /**
     * Get households
     *
     * @return list of all households
     */
    List<HouseholdDTO> findAll();

    /**
     * Update values
     *
     * @param household instance
     */
    void updateHousehold(HouseholdDTO household);

    /**
     * Delete input household TODO may be change to use just id
     *
     * @param household instance
     */
    void deleteHousehold(HouseholdDTO household);

    /**
     * Find household by id
     *
     * @param id value
     * @return Househol d instance
     */
    HouseholdDTO findHouseholdById(Long id);

    /**
     * Remove tenant
     *
     * @param tenant
     */
    void removeTenant(UserDTO tenant, HouseholdDTO h);

    /**
     * Add tenant to tenants and HouseholdDTO to tenant
     *
     * @param tenant
     */
    void addTenant(UserDTO tenant, HouseholdDTO household);

}
