package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public ShoppingItem(Long itemID) {
        this.id = itemID;
    }

    public ShoppingItem() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
