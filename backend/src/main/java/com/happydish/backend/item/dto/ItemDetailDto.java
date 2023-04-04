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
    private long reviewNum;
    private LocalDateTime createdAt;

    @Builder
    public ItemDetailDto(long itemId, String title, String content, String status, String imageUrl,
                         long reviewNum, LocalDateTime createdAt) {
        this.itemId = itemId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.imageUrl = imageUrl;
        this.reviewNum = reviewNum;
        this.createdAt = createdAt;
    }
}
