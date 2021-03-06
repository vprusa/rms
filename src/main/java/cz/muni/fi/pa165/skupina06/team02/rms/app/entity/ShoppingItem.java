package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class representing item for a buy
 *
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

    @Column()
    private Long quantity;

    @ManyToOne
    @JoinColumn()
    private User dedicatedBuyer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ShoppingList shoppingList;

    /**
     * Constructor with specific ID
     *
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
     * @return User who should buy this item
     */
    public User getDedicatedBuyer() {
        return dedicatedBuyer;
    }

    /**
     * @return Shopping list which holds this item
     */
    public ShoppingList getShoppingList() {
        return shoppingList;
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

    /**
     * @param dedicatedBuyer set whom should buy this item
     */
    public void setDedicatedBuyer(User dedicatedBuyer) {
        this.dedicatedBuyer = dedicatedBuyer;
    }

    /**
     * @param shoppingList Shopping list, to which this item belongs to
     */
    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
