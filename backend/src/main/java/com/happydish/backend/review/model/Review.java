package com.happydish.backend.review.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.item.model.Item;
import com.happydish.backend.item.model.Status;
import com.happydish.backend.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Review extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Review(String title, String content, User user, Item item) {
        this.title = title;
        this.content = content;
        this.status = ReviewStatus.ACTIVE;
        this.user = user;
        this.item = item;
    }
}
