package com.happydish.backend.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    @Builder
    public NoticeResponseDto(long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = "관리자";
        this.createdAt = createdAt;
    }


}
