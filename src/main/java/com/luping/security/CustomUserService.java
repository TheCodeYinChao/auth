package com.luping.security;

import com.luping.security.jwt.GrantedAuthorityImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: CustomUserService <br>
 * date: 2020/4/28 18:37 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@Component
public class CustomUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s.equals("admin")){
         Set<String> permissions = new HashSet<>();
         permissions.add("sys:user:view");
            List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
            return new User(s, "123456", true, true, true, true, grantedAuthorities);
        }
        return new User(s, "123456", true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = userService.findPermissions(username);
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(username, user.getPassword(), grantedAuthorities);
    }*/
}
