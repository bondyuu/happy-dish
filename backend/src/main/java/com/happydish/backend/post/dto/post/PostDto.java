package com.happydish.backend.post.dto.post;

import com.happydish.backend.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    @Builder
    public PostDto(String title, String content, UserDto author, String status, String imageUrl, long heartNum, boolean isHeart, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
        this.imageUrl = imageUrl;
        this.heartNum = heartNum;
        this.isHeart = isHeart;
        this.createdAt = createdAt;
    }
}
