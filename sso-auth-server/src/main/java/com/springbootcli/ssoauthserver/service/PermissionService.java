package com.springbootcli.ssoauthserver.service;

import com.springbootcli.ssoauthserver.model.dataobject.PermissionDO;

import java.util.List;

/**
 * @author LuoHaiYang
 */
public interface PermissionService {
    default List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return null;
    }
}
