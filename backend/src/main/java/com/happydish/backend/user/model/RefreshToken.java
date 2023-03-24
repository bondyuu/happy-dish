package com.happydish.backend.user.model;

import com.happydish.backend.global.util.Timestamped;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class RefreshToken extends Timestamped {

    @Id @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String token;

    @Builder
    public RefreshToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
