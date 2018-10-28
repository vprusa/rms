package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class representing item for a buy
 * @author Martin Lacko
 */
@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Long quantity;

    @ManyToOne
    @JoinColumn(nullable = true)
    private User dedicatedBuyer;

    /**
     * Constructor with specific ID
     * @param itemID Specific ID for this item
     */
    public ShoppingItem(Long itemID) {
        this.id = itemID;
    }

    /**
     * Default constructor
     */
    public ShoppingItem() {
    }

    /**
     * @return ID of this item
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * @return Quantity of this item
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param name New name for this item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param quantity set new required quantity
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
