package com.loving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 授权中心安全配置
 * @Author: yijunjun
 * @Date: 2021/2/24 21:56
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LovingUserDetailService userDetailService;

    /**
     * 方法实现说明:用于构建用户认证组件(AuthenticationManagerBuilder,这里典型的是一个建造者模式),
     * 需要传递userDetailsService和密码加密器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里的userDetailService接口是spring security的 我们需要自己
        //写接口来实现，我们在TulingUserDetailService中 查询数据库信息来进行获取用户信息
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }


    /**
     * 密码加密器组件
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 上面的configure真正的构建好了我们的AuthenticationManagerBuilder 我们在这里
     * 需要通过建造者 构建我们的AuthenticationManager对象
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
