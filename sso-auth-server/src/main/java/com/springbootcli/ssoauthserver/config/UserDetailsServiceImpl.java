package com.springbootcli.ssoauthserver.config;

import com.springbootcli.ssoauthserver.model.UserDTO;
import com.springbootcli.ssoauthserver.model.dataobject.PermissionDO;
import com.springbootcli.ssoauthserver.model.dataobject.UserDO;
import com.springbootcli.ssoauthserver.service.PermissionService;
import com.springbootcli.ssoauthserver.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userService.getByUserName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

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
