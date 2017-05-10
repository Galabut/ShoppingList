package com.shopping.dao;

import com.shopping.model.ShoppingList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by JuliaGalabut on 5/8/17.
 */

@Repository("shoppingListDa")
public class ShoppingListDao implements AbstractDao<ShoppingList> {
    private static final Logger logger = LogManager.getLogger(ShoppingListDao.class);

    public ShoppingList getById(String listId, EntityManager em) {
        Query query = em.createQuery("SELECT l FROM ShoppingList l WHERE l.listId=:listId");
        query.setParameter("listId", listId);
        ShoppingList list = null;
        try {
            list = (ShoppingList) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("No list with such id" + e.getMessage());
            return null;
        }
        return list;
    }

    public void create(ShoppingList list, EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(list);
            tx.commit();
        } catch (Exception e) {
            logger.error("Save list: " + e.getMessage());
            tx.rollback();
        }
    }


    @Override
    public void delete(ShoppingList list, EntityManager em) {
        Session session = em.unwrap(Session.class);
        EntityTransaction tx = session.getTransaction();
        list.getUsers().clear();
        try {
            tx.begin();
            session.remove(list);
            tx.commit();
        } catch (Exception e) {
            logger.error("Delete list: " + e.getMessage());
            tx.rollback();
        }
    }

    @Override
    public ShoppingList update(ShoppingList list, EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(list);
            tx.commit();
        } catch (Exception e) {
            logger.error("Update list: " + e.getMessage());
            tx.rollback();
        }
        return list;
    }

}
