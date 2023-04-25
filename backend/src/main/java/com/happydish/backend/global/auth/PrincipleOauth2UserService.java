package com.happydish.backend.global.auth;

import com.happydish.backend.cart.entity.Cart;
import com.happydish.backend.cart.respository.CartRepository;
import com.happydish.backend.user.entity.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipleOauth2UserService extends DefaultOAuth2UserService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //구글 로그인 버튼 -> 구글 로그인 -> 완료 -> code 리턴(Oauth-Client 라이브러리) -> AccessToken요청
        //userRequest 정보 -> loadUser 함수 호출 -> 회원 프로필
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");
        String password = "@#$*892743q#$984sdfhsdf)#@$*(sdf";

        Optional<User> userEntity = userRepository.findByEmail(email);

        User user;
        if(userEntity.isEmpty()){
            user = User.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(user);
            Cart cart = new Cart(user);
            cartRepository.save(cart);
        } else {
            user = userEntity.get();
        }

        // 구글로부터 받은 데이터로 회원가입 처리
        return new PrincipleDetails(user, oAuth2User.getAttributes());
    }
}