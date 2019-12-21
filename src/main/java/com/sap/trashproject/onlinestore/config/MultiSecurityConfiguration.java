package com.sap.trashproject.onlinestore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MultiSecurityConfiguration {

    @Configuration
    @Order(2)
    public static class AdminSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().hasRole("ADMIN")
                    );
        }
    }

    @Configuration
    @Order(3)
    public static class UserSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/user/**")
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().hasRole("USER")
                    );
        }
    }

    @Configuration
    @Order(4)
    public static class PublicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/public/**")
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest()
                                    .permitAll()
                    );
        }
    }

}
