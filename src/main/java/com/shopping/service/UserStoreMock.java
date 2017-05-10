package com.shopping.service;

import org.springframework.stereotype.Service;

/**
 * Created by JuliaGalabut on 5/9/17.
 */
@Service
public class UserStoreMock implements UserStore {
    public final static String MOCK_USER_ID = "abc100";

    @Override
    public String getLoggedInUserId() {
        return MOCK_USER_ID;
    }
}
