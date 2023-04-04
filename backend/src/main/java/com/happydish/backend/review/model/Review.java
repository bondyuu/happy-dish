package com.happydish.backend.review.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.item.model.Item;
import com.happydish.backend.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped {
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
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
