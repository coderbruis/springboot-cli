package com.springcli.ssoauthdemo.config;

import com.google.common.collect.Lists;
import com.springcli.ssoauthdemo.model.UserDTO;
import com.springcli.ssoauthdemo.model.dataobject.PermissionDO;
import com.springcli.ssoauthdemo.model.dataobject.UserDO;
import com.springcli.ssoauthdemo.service.impl.PermissionService;
import com.springcli.ssoauthdemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhy
 * @date 2021/7/3
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userService.getByUserName(username);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        if (userDO != null) {
            // 获取用户授权
            List<PermissionDO> permissionDOS = permissionService.selectPermissionsByUserId(userDO.getId());
            // 声明用户授权
            permissionDOS.forEach(permissionDO -> {
                if (permissionDO != null && permissionDO.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionDO.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }

        return new UserDTO(userDO.getUsername(), userDO.getPassword(), grantedAuthorities);
    }
}
