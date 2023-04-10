package com.happydish.backend.order.model;

import lombok.Getter;

public enum OrderStatus {
    //결제대기, 배송준비, 배송중, 배송완료

    BEFORE_PAYMENT("결제대기"),
    BEFORE_DELIVERY("배송준비"),
    ON_DELIVERY("배송중"),
    AFTER_DELIVERY("배송완료");

    @Getter
    private final String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }
}
