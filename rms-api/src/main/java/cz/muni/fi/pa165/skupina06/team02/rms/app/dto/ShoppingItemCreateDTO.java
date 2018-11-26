package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ShoppingItemCreateDTO {
    @NotNull
    @Size(min = 3, max = 64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
