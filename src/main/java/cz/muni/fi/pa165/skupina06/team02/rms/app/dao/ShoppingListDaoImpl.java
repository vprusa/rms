package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;

/**
 * @author Vojtech Prusa
 *
 */
@Repository
public class ShoppingListDaoImpl implements ShoppingListDao {

    @PersistenceContext
    private EntityManager em;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao#findById(java.
     * lang.Long)
     */
    @Override
    public ShoppingList findById(Long id) {
        return em.find(ShoppingList.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao#create(cz.muni.
     * fi.pa165.skupina06.team02.rms.app.entity.ShoppingList)
     */
    @Override
    public void create(ShoppingList sl) {
        em.persist(sl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao#delete(cz.muni.
     * fi.pa165.skupina06.team02.rms.app.entity.ShoppingList)
     */
    @Override
    public void delete(ShoppingList sl) {
        em.remove(sl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao#findAll()
     */
    @Override
    public List<ShoppingList> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao#findByName(java
     * .lang.String)
     */
    @Override
    public List<ShoppingList> findByName(String name) {
        Query q = em.createQuery("select i from Household i where name = :name", ShoppingList.class);
        q.setParameter(":name", name);
        return q.getResultList();
    }

    @Override
    public ShoppingList update(ShoppingList sl) {
        // TODO Auto-generated method stub
        return em.merge(sl);
    }

}
