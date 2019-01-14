package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.List;

/**
 * @author Martin Lacko, Vojtech Prusa
 */
public class ShoppingListCreateDTO {
    
    private String name;
    private long householdId;
    /**
     * Getter
     *
     * @return name instance
     */
    public String getName() {
        return name;
    }
    /**
     * Setter
     * 
     * @param name instance
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter
     *
     * @return householdId instance
     */
    public long getHouseholdId() {
        return householdId;
    }
    /**
     * Setter
     * 
     * @param householdId instance
     */
    public void setHouseholdId(long householdId) {
        this.householdId = householdId;
    }

}
