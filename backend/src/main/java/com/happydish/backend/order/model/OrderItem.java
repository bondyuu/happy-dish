package com.happydish.backend.order.model;

import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.item.model.Item;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class OrderItem extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public OrderItem(Order order, Item item) {
        this.order = order;
        this.item = item;
        this.status = OrderStatus.BEFORE_PAYMENT;
    }
}
