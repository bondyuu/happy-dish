package com.happydish.backend.post.dto.post;

import com.happydish.backend.post.model.Comment;
import com.happydish.backend.user.dto.UserDto;
import com.happydish.backend.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostDetailDto{
    private long postId;
    private String title;
    private String content;
    private UserDto author;
    private String status;
    private String imageUrl;
    private long heartNum;
    private boolean isHeart;
    private LocalDateTime createdAt;
    private List<Comment> commentList;

    @Builder
    public PostDetailDto(long postId, String title, String content, UserDto author, String status, String imageUrl,
                         long heartNum, boolean isHeart, LocalDateTime createdAt, List<Comment> commentList) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
        this.imageUrl = imageUrl;
        this.heartNum = heartNum;
        this.isHeart = isHeart;
        this.createdAt = createdAt;
        this.commentList = commentList;
    }
}
