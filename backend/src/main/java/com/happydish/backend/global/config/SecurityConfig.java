package com.happydish.backend.global.config;

import com.happydish.backend.global.auth.PrincipleOauth2UserService;
import com.happydish.backend.global.exception.AccessDeniedHandlerException;
import com.happydish.backend.global.exception.AuthenticationEntryPointException;
import com.happydish.backend.global.auth.jwt.JwtFilter;
import com.happydish.backend.global.auth.jwt.TokenProvider;
import com.happydish.backend.global.util.CustomLoginSuccessHandler;
import com.happydish.backend.global.util.CustomOAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final PrincipleOauth2UserService principleOauth2UserService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;
    private final CorsConfigurationSource corsConfigurationSource;

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

        http.cors().configurationSource(corsConfigurationSource);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandlerException())
            .authenticationEntryPoint(new AuthenticationEntryPointException());

        http.httpBasic().disable()
                .authorizeRequests()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/users/save").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/logout/**").permitAll()
//                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .successHandler(customLoginSuccessHandler)
                .and()
                .oauth2Login()
                .successHandler(customOAuth2LoginSuccessHandler)
                .userInfoEndpoint()
                .userService(principleOauth2UserService);

        http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
