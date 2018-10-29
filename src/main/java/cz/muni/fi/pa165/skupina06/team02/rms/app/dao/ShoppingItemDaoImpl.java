package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShoppingItemDaoImpl implements ShoppingItemDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public ShoppingItem findById(Long id) {
        return em.find(ShoppingItem.class, id);
    }

    @Override
    public List<ShoppingItem> findAll() {
        return em.createQuery("select i from ShoppingItems i", ShoppingItem.class)
                .getResultList();
    }

    @Override
    public void create(ShoppingItem item) {
        em.persist(item);
    }

    @Override
    public void delete(ShoppingItem item) {
        em.remove(item);
    }
}
