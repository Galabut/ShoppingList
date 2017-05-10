package com.shopping.controller;

import com.shopping.service.ShoppingListService;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JuliaGalabut on 5/9/17.
 */

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private UserService userService;


}
