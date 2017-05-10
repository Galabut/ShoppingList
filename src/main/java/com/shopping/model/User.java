package com.shopping.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingList> list = new ArrayList<ShoppingList>();


    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invitation> invitationList = new ArrayList<Invitation>();

    public User() {
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public List<ShoppingList> getList() {
        return list;
    }

    public void setList(List<ShoppingList> list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;
    }

    public List<Invitation> getInvitationList() {
        return invitationList;
    }
}
