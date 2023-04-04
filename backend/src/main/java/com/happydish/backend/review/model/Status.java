package com.happydish.backend.review.model;

import lombok.Getter;

public enum Status {
    ACTIVE("활성"),
    USER_DELETED("회원삭제"),
    ADMIN_DELETED("관리자삭제");

    @Getter
    private final String desc;

    Status(String desc) {
        this.desc = desc;
    }
}
