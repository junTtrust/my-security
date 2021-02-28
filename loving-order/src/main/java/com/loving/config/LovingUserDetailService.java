package com.loving.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 该类都是基于内存的 ，后期会改变为db,需要去数据库中查询
 * @Author: yijunjun
 * @Date: 2021/2/27 14:14
 */
//@Component("userDetailService")
@Slf4j
public class LovingUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder  passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("当前登陆用户名为:{}",username);

        return User.builder().username(username)
                .password(passwordEncoder.encode("123456"))
                .authorities("ROLE_ADMIN")
                .build();
    }
}
