package com.shopping.converter;

import com.shopping.model.User;
import com.shopping.vo.UserVo;

/**
 * Created by JuliaGalabut on 5/10/17.
 */
public class UserConverter {

    public static UserVo fromUser(User user) {
        UserVo vo = null;
        if (user != null) {
            vo = new UserVo(
                    user.getUserId(),
                    user.getUserName());
        }
        return vo;
    }


}
