package com.shopping.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by JuliaGalabut on 5/10/17.
 */
public class UserVo {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    public UserVo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}

