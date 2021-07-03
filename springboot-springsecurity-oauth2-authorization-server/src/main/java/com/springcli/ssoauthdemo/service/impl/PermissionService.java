package com.springcli.ssoauthdemo.service.impl;


import com.springcli.ssoauthdemo.model.dataobject.PermissionDO;

import java.util.List;

/**
 * @author LuoHaiYang
 */
public interface PermissionService {
    default List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return null;
    }
}
