package com.luping.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

        return new User(s, "123456", true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
    }
}
