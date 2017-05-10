package com.shopping.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JuliaGalabut on 5/6/17.
 */

@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @Column(name = "list_id")
    private String listId;

    @Column(name = "list_name")
    private String listName;

    @Column(name = "created_when")
    private Date createdWhen;

    @Column(name = "updated_when")
    private Date updatedWhen;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Item> items = new ArrayList<Item>();


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_lists",
            joinColumns = {@JoinColumn(name = "list_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<User>();

    public ShoppingList() {
    }

    public ShoppingList(String listName) {
        this.listName = listName;
    }

    public String getListId() {
        return listId;
    }

    public String getListName() {
        return listName;
    }

    public List<User> getUsers() {
        return users;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public Date getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Date updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }


}
