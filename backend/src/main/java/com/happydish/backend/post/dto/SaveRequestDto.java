package com.happydish.backend.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveRequestDto {
    private String title;
    private String content;

}
