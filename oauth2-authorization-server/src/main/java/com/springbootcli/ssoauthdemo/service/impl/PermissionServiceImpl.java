package com.springbootcli.ssoauthdemo.service.impl;

import com.springbootcli.ssoauthdemo.mapper.PermissionMapper;
import com.springbootcli.ssoauthdemo.service.PermissionService;
import com.springbootcli.ssoauthdemo.model.dataobject.PermissionDO;
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
