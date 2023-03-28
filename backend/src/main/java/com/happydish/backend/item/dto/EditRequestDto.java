package com.happydish.backend.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EditRequestDto {
    private String title;
    private String content;
    private String imageUrl;
}
