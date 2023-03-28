package com.happydish.backend.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveRequestDto {
    private String title;
    private String content;

}
