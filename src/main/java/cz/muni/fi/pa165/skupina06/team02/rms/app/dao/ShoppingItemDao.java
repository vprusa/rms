package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;

import java.util.List;

public interface ShoppingItemDao {
    public ShoppingItem findById(Long id);

    public List<ShoppingItem> findAll();

    public void create(ShoppingItem item);

    public void delete(ShoppingItem item);

    public void update(ShoppingItem item);
}
