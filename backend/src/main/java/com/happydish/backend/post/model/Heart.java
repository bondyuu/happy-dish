package com.happydish.backend.post.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.user.model.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Heart extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
