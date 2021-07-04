package com.springbootcli.ssoauthserver.service;

import com.springbootcli.ssoauthserver.model.dataobject.UserDO;

/**
 * @author LuoHaiYang
 */
public interface UserService {
    default UserDO getByUserName(String username) {
        return null;
    }
}
