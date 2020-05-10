package com.roderick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*.antMatchers("/management/**").hasRole("admin")*/
                .antMatchers("/**").permitAll();
        http.formLogin()
                .loginPage("/authentication/login")
                .loginProcessingUrl("/authentication/form")
                .successForwardUrl("/management/mainPage")
                .defaultSuccessUrl("/");
        http.csrf().disable();  //解除对于post请求的拦截，不然文件上传玩不了
    }

    @Bean
    public UserDetailsService user() {
        UserDetails user = User.builder()  //withDefaultPasswordEncoder不加密的方式（明文账号密码）
                .username("root")
                .password("{bcrypt}$2a$10$7o6tRDH/4chLQ1uj4W279eCz4.Y2RV/LbXSDacxc6WRsy9/vFC6ou")   //加密方式，防止泄露源码
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
