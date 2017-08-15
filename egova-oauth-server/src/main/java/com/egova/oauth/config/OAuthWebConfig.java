package com.egova.oauth.config;

import com.egova.oauth.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import javax.sql.DataSource;

/**
 * 配置用户信息，以及受保护路径、允许访问路径
 */
@Configuration
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class OAuthWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
                .anyRequest().authenticated();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //        auth.inMemoryAuthentication()
        //                .withUser("reader")
        //                .password("reader")
        //                .authorities("FOO_READ")
        //                .and()
        //                .withUser("writer")
        //                .password("writer")
        //                .authorities("FOO_READ", "FOO_WRITE");

        //        UserDetails userDetails = userDetailsService().loadUserByUsername("reader");
        //        System.out.println(userDetails.getPassword());

        auth.userDetailsService(customUserService());
        auth.jdbcAuthentication().dataSource(dataSource);
        UserDetails userDetails = customUserService().loadUserByUsername("reader");
        System.out.println(userDetails.getPassword());
    }

}
