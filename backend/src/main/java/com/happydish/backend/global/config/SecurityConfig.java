package com.happydish.backend.global.config;

import com.happydish.backend.global.auth.PrincipleOauth2UserService;
import com.happydish.backend.global.util.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipleOauth2UserService principleOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable()

                .headers()
                .frameOptions()
                .disable();

        http.authorizeRequests()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/users/loginsuccess")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principleOauth2UserService);
    }
}
