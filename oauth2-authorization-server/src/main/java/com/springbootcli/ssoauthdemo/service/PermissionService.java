package com.springbootcli.ssoauthdemo.service;


import com.springbootcli.ssoauthdemo.model.dataobject.PermissionDO;

import java.util.List;

/**
 * @author LuoHaiYang
 */
public interface PermissionService {
    default List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return null;
    }
}
