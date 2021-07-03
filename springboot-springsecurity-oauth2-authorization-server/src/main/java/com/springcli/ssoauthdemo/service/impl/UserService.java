package com.springcli.ssoauthdemo.service.impl;


import com.springcli.ssoauthdemo.model.dataobject.UserDO;

/**
 * @author LuoHaiYang
 */
public interface UserService {
    default UserDO getByUserName(String username) {
        return null;
    }
}
