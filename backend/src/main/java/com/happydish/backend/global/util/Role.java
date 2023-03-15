package com.happydish.backend.global.util;

import lombok.Getter;

public enum Role {
    ROLE_USER("일반사용자"),
    ROLE_ADMIN("관리자");

    @Getter
    private final String desc;

    Role(String desc) {
        this.desc = desc;
    }
}
