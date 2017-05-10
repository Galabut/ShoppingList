package com.shopping.service;

import com.shopping.dao.UserDao;
import com.shopping.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public void saveUser(User user, EntityManager em) {
        dao.create(user, em);
    }

    public User updateUser(User user, EntityManager em) {
        return dao.update(user, em);
    }

    public User findUserByid(String userId, EntityManager em) {
        return dao.getById(userId, em);
    }
}
