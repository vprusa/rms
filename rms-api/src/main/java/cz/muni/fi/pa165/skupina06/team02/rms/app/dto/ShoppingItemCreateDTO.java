package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ShoppingItemCreateDTO {
    
    private Long quantity = 1l;
    //private Boolean bought = false;
    private long buyerId;
    private long shoppingListId;

    
    @NotNull
    @Size(min = 3, max = 64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     *
     * @return quantity instance
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Setter
     * 
     * @param quantity instance
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter
     *
     * @return buyerId instance
     */
    public long getBuyerId() {
        return buyerId;
    }

    /**
     * Setter
     * 
     * @param buyerId instance
     */
    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter
     *
     * @return shoppingListId instance
     */
    public long getShoppingListId() {
        return shoppingListId;
    }

    /**
     * Setter
     * 
     * @param shoppingListId instance
     */
    public void setShoppingListId(long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItemCreateDTO that = (ShoppingItemCreateDTO) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShoppingItemCreateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
