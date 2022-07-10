package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .mvcMatchers("/","/info").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin();
        http.httpBasic();
    }

    // user 정보 임의로 여러 개 설정하기
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("spring").password("{noop}123").roles("USER").and()
                .withUser("admin").password("{noop}!@#").roles("ADMIN");
    }
}
