package com.happydish.backend.cart.entity;

import com.happydish.backend.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Cart {
    @Id @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> itemList = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }
}
