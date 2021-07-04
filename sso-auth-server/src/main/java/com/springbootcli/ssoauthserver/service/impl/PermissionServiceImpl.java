package com.springbootcli.ssoauthserver.service.impl;
import com.springbootcli.ssoauthserver.mapper.PermissionMapper;
import com.springbootcli.ssoauthserver.model.dataobject.PermissionDO;
import com.springbootcli.ssoauthserver.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return permissionMapper.selectPermissionsByUserId(userId);
    }
}
