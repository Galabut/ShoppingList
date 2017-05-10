package com.shopping.converter;

import com.shopping.model.Item;
import com.shopping.vo.ItemVo;

import java.util.UUID;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
public class ItemConverter {

    public static ItemVo fromItem(Item item) {
        ItemVo vo = null;
        if (item != null) {
            vo = new ItemVo(
                    item.getItemId(),
                    item.getItemName(),
                    item.getMarkedAsChecked(),
                    item.getQuantityDescription());
        }
        return vo;
    }

    public static Item fromItemVo(ItemVo vo) {
        Item item = null;
        if (vo != null) {
            item = new Item(
                    vo.getId() != null ? vo.getId() : UUID.randomUUID().toString(),
                    vo.getItemName(),
                    vo.getQuantityDescription(),
                    vo.getMarkedAsChecked());
        }
        return item;
    }
}
