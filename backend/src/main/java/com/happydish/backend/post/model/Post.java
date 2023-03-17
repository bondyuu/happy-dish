package com.happydish.backend.post.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.user.model.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    private PostStatus status;
    @Column
    private String imageUrl;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Heart> heartList = new ArrayList<>();

}
