package com.happydish.backend.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;
}
