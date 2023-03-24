package com.happydish.backend.post.dto.comment;

import com.happydish.backend.post.dto.post.PostDto;
import com.happydish.backend.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private String Content;
    private UserDto user;
    private PostDto post;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
