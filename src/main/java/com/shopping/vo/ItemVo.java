package com.shopping.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
public class ItemVo {

    @JsonProperty("item_id")
    private String id;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("marked_as_checked")
    private Boolean markedAsChecked;

    @JsonProperty("quantity_desc")
    private String quantityDescription;

    public ItemVo() {
    }

    public ItemVo(String id, String itemName, Boolean markedAsChecked, String quantityDescription) {
        this.id = id;
        this.itemName = itemName;
        this.markedAsChecked = markedAsChecked;
        this.quantityDescription = quantityDescription;
    }

    public String getId() {
        return id;
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
}
