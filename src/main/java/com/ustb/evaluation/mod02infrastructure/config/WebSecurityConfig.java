package com.ustb.evaluation.mod02infrastructure.config;

import com.ustb.evaluation.mod02infrastructure.filter.JwtAuthorizationFilter;
import com.ustb.evaluation.mod02infrastructure.filter.JwtLoginFilter;
import com.ustb.evaluation.mod02infrastructure.prop.RsaKeyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //    @Autowired
//    private SysUserService sysUserService;
    @Autowired
    private RsaKeyProperties prop;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //  daoAuthenticationProvider.setUserDetailsService(sysUserService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public void configure(HttpSecurity http) throws Exception {
        //禁用csrf保护机制
        http.csrf().disable();
        //禁用cors保护机制
        http.cors().disable();
        //禁用session会话
        http.sessionManagement().disable();
        //禁用form表单登录
        http.formLogin().disable();
        //增加自定义认证过滤器（认证服务需要配置）
      //  http.addFilter(new JwtLoginFilter(super.authenticationManager(), prop));//这个以后就不用了，不认证，直接携带token
        http.addFilter(new JwtAuthorizationFilter(super.authenticationManager(), prop));


    }
}