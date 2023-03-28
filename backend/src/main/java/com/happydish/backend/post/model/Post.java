package com.happydish.backend.post.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.post.dto.post.EditRequestDto;
import com.happydish.backend.post.dto.post.PostDetailDto;
import com.happydish.backend.post.dto.post.PostDto;
import com.happydish.backend.user.model.Role;
import com.happydish.backend.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private String imageUrl;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Heart> heartList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(String title, String content,User user, String url) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.status = Status.ACTIVE;
        this.imageUrl = url;
    }

    public void deletedBy(Role role) {
        if (role.equals(Role.ROLE_ADMIN)) {
            this.status = Status.ADMIN_DELETED;
        } else {
            this.status = Status.USER_DELETED;
        }
    }

    public void edit(EditRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        if (!requestDto.getImageUrl().equals("")) {
            this.imageUrl = requestDto.getImageUrl();
        }
    }

    public PostDto toPostDto() {
        return PostDto.builder()
                .postId(this.id)
                .title(this.title)
                .content(this.content)
                .author(this.user.toUserDto())
                .status(this.status.toString())
                .imageUrl(this.imageUrl)
                .heartNum(this.heartList.size())
                .createdAt(this.getCreatedAt())
                .build();
    }

    public PostDetailDto toDetailDtl() {
        return PostDetailDto.builder()
                .postId(this.id)
                .title(this.title)
                .content(this.content)
                .author(this.user.toUserDto())
                .status(this.status.toString())
                .imageUrl(this.imageUrl)
                .heartNum(this.heartList.size())
                .createdAt(this.getCreatedAt())
                .commentList(this.commentList)
                .build();
    }
}
