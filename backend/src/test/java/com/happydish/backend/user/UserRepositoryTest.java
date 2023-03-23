package com.happydish.backend.user;


import com.happydish.backend.user.model.Role;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.model.UserStatus;
import com.happydish.backend.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private final String TEST_EMAIL = "test@test.com";
    private final String TEST_NAME = "test";
    private final String TEST_URL = "profileUrl";
    private final String TEST_PASSWORD = "password";

    @Test
    @DisplayName("Form Login User Save")
    void saveFormUserTest() {
        User user = User.builder()
                .email(TEST_EMAIL)
                .name(TEST_NAME)
                .profileUrl(TEST_URL)
                .password(TEST_PASSWORD)
                .build();

        User savedUser =  userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(savedUser.getName()).isEqualTo(TEST_NAME);
        assertThat(savedUser.getProfileUrl()).isEqualTo(TEST_URL);
        assertThat(savedUser.getPassword()).isEqualTo(TEST_PASSWORD);
        assertThat(savedUser.getRole()).isEqualTo(Role.ROLE_USER);
        assertThat(savedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    @DisplayName("Find By Email")
    void findByEmailTest() {
        User user = User.builder()
                .email(TEST_EMAIL)
                .name(TEST_NAME)
                .profileUrl(TEST_URL)
                .password(TEST_PASSWORD)
                .build();

        userRepository.save(user);

        User savedUser = userRepository.findByEmail(TEST_EMAIL)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(savedUser.getName()).isEqualTo(TEST_NAME);
        assertThat(savedUser.getProfileUrl()).isEqualTo(TEST_URL);
        assertThat(savedUser.getPassword()).isEqualTo(TEST_PASSWORD);
        assertThat(savedUser.getRole()).isEqualTo(Role.ROLE_USER);
        assertThat(savedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);


    }
}
