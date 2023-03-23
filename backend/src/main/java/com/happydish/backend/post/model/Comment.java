package com.happydish.backend.post.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.post.dto.comment.CommentDto;
import com.happydish.backend.user.model.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String content;
    @Column
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.status = Status.ACTIVE;
        this.user = user;
        this.post = post;
    }

    public CommentDto toCommentDto() {
        return CommentDto.builder()
                .Content(this.content)
                .user(this.user.toUserDto())
                .post(this.post.toPostDto())
                .createdAt(this.getCreatedAt())
                .modifiedAt(this.getModifiedAt())
                .build();
    }

}
