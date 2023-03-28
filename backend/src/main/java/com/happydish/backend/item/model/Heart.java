package com.happydish.backend.item.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Heart extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
