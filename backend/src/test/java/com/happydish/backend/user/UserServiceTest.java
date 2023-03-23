package com.happydish.backend.user;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.user.dto.EditRequestDto;
import com.happydish.backend.user.dto.SignupRequestDto;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
import com.happydish.backend.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private final String TEST_EMAIL = "test@test.com";
    private final String TEST_NAME = "test";
    private final String TEST_URL = "profileUrl";
    private final String TEST_PASSWORD = "password";

    @Test
    @DisplayName("Sign Up Test")
    @Transactional
    void testSignUp() {
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setName(TEST_NAME);
        requestDto.setPassword(TEST_PASSWORD);


        ResponseEntity<?> firstResponse = userService.save(requestDto);

        assertThat(firstResponse.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(firstResponse.getBody()).isEqualTo("ok");

        ResponseEntity<?> secondResponse = userService.save(requestDto);

        assertThat(secondResponse.getStatusCode().toString()).isEqualTo("400 BAD_REQUEST");
        assertThat(secondResponse.getBody()).isEqualTo("중복된 회원");
    }

    @Test
    @DisplayName("User Edit Test")
    void testEdit() {
        User user = User.builder()
                .email(TEST_EMAIL)
                .name(TEST_NAME)
                .profileUrl(TEST_URL)
                .password(TEST_PASSWORD)
                .build();

        userRepository.save(user);

        EditRequestDto requestDto = new EditRequestDto();
        requestDto.setPassword("2345");

        try {
            ResponseEntity<?> response = userService.edit(requestDto,null,new PrincipleDetails(user));
            assertThat(response.getBody()).isEqualTo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
