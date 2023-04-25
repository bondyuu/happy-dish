package com.happydish.backend.notice.dto;

import com.happydish.backend.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {
    private String title;
    private String content;

    @Builder
    public NoticeRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notice toEntity() {
        return Notice.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
