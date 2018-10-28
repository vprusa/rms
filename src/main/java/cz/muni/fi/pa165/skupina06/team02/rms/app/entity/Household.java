package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
