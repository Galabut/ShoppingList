package com.shopping.dao;

import com.shopping.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by JuliaGalabut on 5/8/17.
 */

@Repository("userDao")
public class UserDao implements AbstractDao<User> {
    EntityManager em;

    @Override
    public void create(User entity, EntityManager em) {

    }

    @Override
    public void delete(User entity, EntityManager em) {

    }

    @Override
    public User update(User entity, EntityManager em) {
        return null;
    }

    public User getById(String userId, EntityManager em) {
        Query query = em.createQuery("SELECT u from User u where u.userId=:userId");
        query.setParameter("userId", userId);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

}
