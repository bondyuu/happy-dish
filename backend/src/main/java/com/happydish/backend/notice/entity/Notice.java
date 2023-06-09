package com.happydish.backend.notice.entity;


import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.notice.dto.NoticeRequestDto;
import com.happydish.backend.notice.dto.NoticeResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends Timestamped {

    @Id @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String content;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoticeResponseDto toResponseDto() {
        return NoticeResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .createdAt(this.getCreatedAt())
                .build();
    }

    public void edit(NoticeRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
