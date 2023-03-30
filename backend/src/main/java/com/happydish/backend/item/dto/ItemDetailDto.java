package com.happydish.backend.item.dto;

import com.happydish.backend.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ItemDetailDto {
    private long itemId;
    private String title;
    private String content;
    private String status;
    private String imageUrl;
    private long heartNum;
    private boolean isHeart;
    private LocalDateTime createdAt;

    @Builder
    public ItemDetailDto(long itemId, String title, String content, String status, String imageUrl,
                         long heartNum, boolean isHeart, LocalDateTime createdAt) {
        this.itemId = itemId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.imageUrl = imageUrl;
        this.heartNum = heartNum;
        this.isHeart = isHeart;
        this.createdAt = createdAt;
    }
}
