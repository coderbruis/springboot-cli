package com.springbootcli.ssoauthdemo.config;

import com.google.common.collect.Lists;
import com.springbootcli.ssoauthdemo.model.dataobject.UserDO;
import com.springbootcli.ssoauthdemo.service.PermissionService;
import com.springbootcli.ssoauthdemo.service.UserService;
import com.springbootcli.ssoauthdemo.model.UserDTO;
import com.springbootcli.ssoauthdemo.model.dataobject.PermissionDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lhy
 * @date 2021/7/3
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
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
