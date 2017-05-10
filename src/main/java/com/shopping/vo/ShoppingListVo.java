package com.shopping.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
public class ShoppingListVo {

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("list_name")
    private String listName;

    @JsonProperty("created_when")
    private String createdWhen;

    @JsonProperty("updated_when")
    private String updatedWhen;

    @JsonProperty("items")
    private List<ItemVo> itemVos;

    @JsonProperty("users")
    private List<UserVo> userVos;

    public ShoppingListVo() {
    }

    public ShoppingListVo(String listId, String listName, String createdWhen, String updatedWhen) {
        this.listId = listId;
        this.listName = listName;
        this.createdWhen = createdWhen;
        this.updatedWhen = updatedWhen;
    }

    public String getListId() {
        return listId;
    }

    public String getListName() {
        return listName;
    }

    public String getCreatedWhen() {
        return createdWhen;
    }

    public String getUpdatedWhen() {
        return updatedWhen;
    }

    public List<ItemVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<ItemVo> itemVos) {
        this.itemVos = itemVos;
    }

    public List<UserVo> getUserVos() {
        return userVos;
    }

    public void setUserVos(List<UserVo> userVos) {
        this.userVos = userVos;
    }
}
