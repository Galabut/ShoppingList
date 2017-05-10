package com.shopping.model;

import javax.persistence.*;

@Entity
@Table(name = "list_items")
public class Item {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "marked_as_checked")
    private Boolean markedAsChecked;

    @Column(name = "quantity_desc")
    private String quantityDescription;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;

    public Item() {
    }

    public Item(String itemId, String itemName, String quantityDescription, Boolean markedAsChecked) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.markedAsChecked = markedAsChecked;
        this.quantityDescription = quantityDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public Boolean getMarkedAsChecked() {
        return markedAsChecked;
    }

    public String getQuantityDescription() {
        return quantityDescription;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setMarkedAsChecked(Boolean markedAsChecked) {
        this.markedAsChecked = markedAsChecked;
    }

    public void setQuantityDescription(String quantityDescription) {
        this.quantityDescription = quantityDescription;
    }

    public String getItemId() {
        return itemId;
    }

}
