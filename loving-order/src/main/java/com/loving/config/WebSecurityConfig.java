package com.loving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.web.client.RestTemplate;

/**
 * 资源服务器 要去验证token的配置
 * @Author: yijunjun
 * @Date: 2021/2/27 17:27
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private LovingUserDetailService userDetailService;

    /**
     * 资源服务器 要去资源服务器校验的的bean
     */
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("order_app");
        remoteTokenServices.setClientSecret("loving");
        remoteTokenServices.setCheckTokenEndpointUrl("http://auth-server/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate);
        //校验令牌只会拿到用户名,但是我需要获取到整个的用户信息  就需要这个转换器
        //remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }

    /**
     * 资源服务器 要去资源服务器校验的的bean  该bean会作为属性塞入到
     * OAuth2AuthenticationProcessingFilter过滤器，该过滤器就会通过AuthenticationManager
     * 中的resourceServerTokenServices组件去 认证服务器 校验我们的token。
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() {
        OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
        manager.setTokenServices(resourceServerTokenServices());
        return manager;
    }

    /*@Bean
    public AccessTokenConverter accessTokenConverter() {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailService);
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        return accessTokenConverter;
    }*/
}
