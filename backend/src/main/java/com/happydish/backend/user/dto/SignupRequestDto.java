package com.happydish.backend.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;
}
