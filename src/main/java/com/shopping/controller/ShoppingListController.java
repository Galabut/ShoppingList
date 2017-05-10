package com.shopping.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shopping.JPAUtility;
import com.shopping.converter.ShoppingListConverter;
import com.shopping.model.ShoppingList;
import com.shopping.model.User;
import com.shopping.service.ShoppingListService;
import com.shopping.service.UserService;
import com.shopping.service.UserStore;
import com.shopping.vo.ShoppingListVo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/list")
public class ShoppingListController {

    private static final Logger logger = LogManager.getLogger(ShoppingListController.class);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserStore userStore;


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity createList(
            @RequestBody JsonNode listNode) {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            if (user != null) {
                ShoppingListVo listVo = mapper.treeToValue(listNode, ShoppingListVo.class);
                ShoppingList list = ShoppingListConverter.fromShoppingListVo(listVo);

                list.setListId(UUID.randomUUID().toString());
                Date date = new Date();
                list.setCreatedWhen(date);
                list.setUpdatedWhen(date);
                list.getUsers().add(user);
                list.getItems().forEach(item -> item.setShoppingList(list));

                user.getList().add(list);

                shoppingListService.saveShoppingList(list, em);
                response = ResponseEntity.ok(wrapMessage("list_id", list.getListId()));
            } else response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user"));
        } catch (Exception e) {
            logger.error("Error to create list: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        } finally {
            em.close();
        }
        return response;
    }


    @RequestMapping(value = "/{list_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(
            @PathVariable(name = "list_id") String listId,
            @RequestBody JsonNode listNode) {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            ShoppingList list = shoppingListService.findShoppingListById(listId, user, em);
            if (user != null) {
                if (list != null) {
                    ShoppingListVo listVo = mapper.treeToValue(listNode, ShoppingListVo.class);
//                    list.getItems().removeAll(list.getItems());
                    list.getItems().clear();
                    list.getItems().addAll(ShoppingListConverter.fromShoppingListVo(listVo).getItems());
                    list.getItems().forEach(item -> item.setShoppingList(list));
                    Date updateWhen = new Date();
                    list.setUpdatedWhen(updateWhen);

                    shoppingListService.updateShoppingList(list, user, em);
                    JsonNode responseNode = wrapMessage("updated_when", format.format(updateWhen));
                    response = ResponseEntity.ok(responseNode);
                } else
                    response = ResponseEntity.badRequest().body(wrapMessage("error", "No such shopping list exists for User"));
            } else
                response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user")); //todo Can add error codes
        } catch (Exception e) {
            logger.error("Error to update list: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        } finally {
            em.close();
        }
        return response;
    }


    @RequestMapping(value = "/{list_id}/last_update", method = RequestMethod.GET)
    public ResponseEntity getLastUpdateDate(
            @PathVariable(name = "list_id") String listId) {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        Date lastUpdate = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            ShoppingList list = shoppingListService.findShoppingListById(listId, user, em);
            if (user != null) {
                if (list != null) {
                    lastUpdate = list.getUpdatedWhen();
                    JsonNode responseNode = wrapMessage("updated_when", format.format(lastUpdate));
                    response = ResponseEntity.ok(responseNode);
                } else
                    response = ResponseEntity.badRequest().body(wrapMessage("error", "No such shopping list exists for User"));
            } else response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user"));
        } catch (Exception e) {
            logger.error("Error to get date updated_when: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        }
        return response;
    }

    @RequestMapping(value = "/{list_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getList(
            @PathVariable(name = "list_id") String listId) {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            ShoppingList list = shoppingListService.findShoppingListById(listId, user, em);
            if (user != null) {
                if (list != null) {
                    response = ResponseEntity.ok(ShoppingListConverter.fromShoppingList(list));
                } else
                    response = ResponseEntity.badRequest().body(wrapMessage("error", "No such shopping list exists for User"));
            } else response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user"));
        } catch (Exception e) {
            logger.error("Error to get list: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        }
        return response;
    }

    @RequestMapping(value = "/{list_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteList(
            @PathVariable(name = "list_id") String listId) {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            ShoppingList list = shoppingListService.findShoppingListById(listId, user, em);
            if (user != null) {
                shoppingListService.removeShoppingList(list, user, em);
                response = ResponseEntity.ok(wrapMessage("status", "deleted"));
            } else
                response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user"));
        } catch (Exception e) {
            logger.error("Error to delete list: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        } finally {
            em.close();
        }
        return response;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllLists() {
        EntityManager em = JPAUtility.getEntityManager();
        ResponseEntity response = null;
        try {
            User user = userService.findUserByid(userStore.getLoggedInUserId(), em);
            if (user != null) {
                List<ShoppingList> shoppingLists = user.getList();
                List<ShoppingListVo> vos = shoppingLists.stream().map(ShoppingListConverter::fromShoppingList).collect(Collectors.toList());
                response = ResponseEntity.ok(vos);
            } else
                response = ResponseEntity.badRequest().body(wrapMessage("error", "No such user"));
        } catch (Exception e) {
            logger.error("Error to delete list: " + e.getMessage());
            response = ResponseEntity.badRequest().body(wrapMessage("error", "Server error"));
        } finally {
            em.close();
        }
        return response;
    }

    private JsonNode wrapMessage(String key, String value) {
        return JsonNodeFactory.instance.objectNode().put(key, value);
    }

}
