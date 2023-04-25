package com.happydish.backend.cart.entity;

import com.happydish.backend.item.entity.Item;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CartItem {
    @Id @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public CartItem(Cart cart, Item item) {
        this.cart = cart;
        this.item = item;
    }
}
