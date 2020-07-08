package com.wulf.systems.mes.MESSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/attribute/**").hasRole("ADMIN")
                .antMatchers("/order/**").hasRole("ADMIN")
                .antMatchers("/order-products-attribute-value/**").hasRole("ADMIN")
                .antMatchers("/order-products/**").hasRole("ADMIN")
                .antMatchers("/product-attribute/**").hasRole("ADMIN")
                .antMatchers("/product/**").hasRole("ADMIN")
                .antMatchers("/property/**").hasRole("ADMIN")
                .antMatchers("/workstation-config/**").hasRole("ADMIN")
                .antMatchers("/workstation/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}123").roles("USER")
                .and()
                .withUser("admin").password("{noop}123").roles("USER", "ADMIN");

    }
}
