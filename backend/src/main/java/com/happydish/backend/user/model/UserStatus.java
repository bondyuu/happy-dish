package com.happydish.backend.user.model;

import lombok.Getter;

public enum UserStatus {
    ACTIVE("활성회원"),
    DELETED("탈퇴회원"),
    BANNED("추방회원");

    @Getter
    private final String desc;

    UserStatus(String desc) {
        this.desc = desc;
    }
}
