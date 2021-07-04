package com.springbootcli.ssoauthdemo.service;


import com.springbootcli.ssoauthdemo.model.dataobject.UserDO;

/**
 * @author LuoHaiYang
 */
public interface UserService {
    default UserDO getByUserName(String username) {
        return null;
    }
}
