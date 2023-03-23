package com.happydish.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MypageResponseDto {
    private UserDto user;
    private long heartNum;

}
