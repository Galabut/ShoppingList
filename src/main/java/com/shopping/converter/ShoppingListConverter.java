package com.shopping.converter;

import com.shopping.model.ShoppingList;
import com.shopping.vo.ShoppingListVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class ShoppingListConverter {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static ShoppingListVo fromShoppingList(ShoppingList list) {
        ShoppingListVo vo = null;
        if (list != null) {
            vo = new ShoppingListVo(
                    list.getListId(),
                    list.getListName(),
                    format.format(list.getCreatedWhen()),
                    format.format(list.getUpdatedWhen())
            );

            vo.setItemVos(list.getItems().stream().map(ItemConverter::fromItem).collect(Collectors.toList()));
            vo.setUserVos(list.getUsers().stream().map(UserConverter::fromUser).collect(Collectors.toList()));
        }
        return vo;
    }

    public static ShoppingList fromShoppingListVo(ShoppingListVo vo) throws ParseException {
        ShoppingList list = null;
        if (vo != null) {
            list = new ShoppingList(vo.getListName());
            list.setItems(vo.getItemVos().stream().map(ItemConverter::fromItemVo).collect(Collectors.toList()));
        }
        return list;
    }
}

