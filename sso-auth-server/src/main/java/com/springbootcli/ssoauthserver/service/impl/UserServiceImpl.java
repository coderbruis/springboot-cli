package com.springbootcli.ssoauthserver.service.impl;
import com.springbootcli.ssoauthserver.mapper.UserMapper;
import com.springbootcli.ssoauthserver.model.dataobject.UserDO;
import com.springbootcli.ssoauthserver.service.UserService;
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
