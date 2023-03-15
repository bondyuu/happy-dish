package com.happydish.backend.user.service;

import com.happydish.backend.user.dto.SignupRequestDto;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> save(SignupRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("중복된 회원");
        }

        User user = User.builder()
                        .email(requestDto.getEmail())
                        .name(requestDto.getName())
                        .password(requestDto.getPassword())
                        .build();

        userRepository.save(user);
        return ResponseEntity.ok("ok");
    }
}
