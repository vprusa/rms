package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Vojtech Prusa
 *
 */
@Entity
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private String buildingNumber;

    @NotNull
    @Column(nullable = false)
    private String zipCode;

    @NotNull
    @Column(nullable = false)
    private String state;

    @OneToMany
    private List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

    /**
     * @return shopping list instance
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * @param shoppingLists instance
     */
    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    /**
     * Adds Shopping list to list of Shopping lists of Household
     * 
     * @param shoppingList instance
     */
    public void addShoppingList(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street value
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return building number
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @param buildingNumber value
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode value
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state value
     */
    public void setState(String state) {
        this.state = state;
    }
}
