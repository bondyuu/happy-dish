package com.happydish.backend.global.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.happydish.backend.global.jwt.TokenProvider;
import com.happydish.backend.user.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        TokenDto token = tokenProvider.generateToken(authentication);

        response.getWriter().write(objectMapper.writeValueAsString(token));
    }
}
