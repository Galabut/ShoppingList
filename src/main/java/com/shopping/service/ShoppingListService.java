package com.shopping.service;

import com.shopping.dao.ShoppingListDao;
import com.shopping.model.ShoppingList;
import com.shopping.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListDao dao;

    public void saveShoppingList(ShoppingList list, EntityManager em) {
        dao.create(list, em);
    }

    public ShoppingList findShoppingListById(String listId, User user, EntityManager em) {
        if (listBelongsToUser(user, listId)) {
            return dao.getById(listId, em);
        } else
            return null;
    }

    public ShoppingList updateShoppingList(ShoppingList list, User user, EntityManager em) {
        if (listBelongsToUser(user, list.getListId())) {
            return dao.update(list, em);
        } else
            return null;
    }

    public void removeShoppingList(ShoppingList list, User user, EntityManager em) {
        if (listBelongsToUser(user, list.getListId())) {
            dao.delete(list, em);
        }
    }

    private Boolean listBelongsToUser(User user, String listId) {
        return user.getList().stream().anyMatch(list -> list.getListId().equals(listId));
    }
}
