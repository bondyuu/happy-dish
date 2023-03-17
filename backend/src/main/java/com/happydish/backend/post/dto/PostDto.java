package com.happydish.backend.post.dto;

import com.happydish.backend.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private UserDto author;
    private String status;
    private String imageUrl;
    private long heartNum;
    private boolean isHeart;

    @Builder
    public PostDto(String title, String content, UserDto author, String status, String imageUrl, long heartNum, boolean isHeart) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
        this.imageUrl = imageUrl;
        this.heartNum = heartNum;
        this.isHeart = isHeart;
    }
}
