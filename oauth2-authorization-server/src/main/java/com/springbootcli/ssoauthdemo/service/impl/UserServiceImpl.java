package com.springbootcli.ssoauthdemo.service.impl;

import com.springbootcli.ssoauthdemo.mapper.UserMapper;
import com.springbootcli.ssoauthdemo.model.dataobject.UserDO;
import com.springbootcli.ssoauthdemo.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author LuoHaiYang
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDO getByUserName(String username) {
        Example example = new Example(UserDO.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }
}
