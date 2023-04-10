package com.happydish.backend.order.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.user.model.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Order extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> itemList = new ArrayList<>();

    @Builder
    public Order(User user) {
        this.user = user;
    }
}
