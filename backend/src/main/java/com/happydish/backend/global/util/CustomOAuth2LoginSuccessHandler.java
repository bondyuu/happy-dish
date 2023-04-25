package com.happydish.backend.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.global.auth.jwt.TokenProvider;
import com.happydish.backend.user.dto.TokenDto;
import com.happydish.backend.user.entity.RefreshToken;
import com.happydish.backend.user.entity.User;
import com.happydish.backend.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;


    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        TokenDto token = tokenProvider.generateToken(authentication);
        String refreshToken = token.getRefreshToken();

        PrincipleDetails principleDetails = (PrincipleDetails) authentication.getPrincipal();
        User user = principleDetails.getUser();

        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser(user);
        optionalRefreshToken.ifPresent(refreshTokenRepository::delete);

        refreshTokenRepository.save(RefreshToken.builder().user(user).token(refreshToken).build());

        String target = UriComponentsBuilder.fromUriString("http://localhost:8081/oauth/redirect")
                        .queryParam("at", token.getGrantType()+" "+token.getAccessToken())
                        .queryParam("rt", token.getRefreshToken())
                        .build().toUriString();
        getRedirectStrategy().sendRedirect(request,response, target);

    }
}