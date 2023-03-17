package com.happydish.backend.post.model;

import lombok.Getter;

public enum PostStatus {
    ACTIVE("활성게시글"),
    USER_DELETED("회원삭제게시글"),
    ADMIN_DELETED("관리자삭제게시글");

    @Getter
    private final String desc;

    PostStatus(String desc) {
        this.desc = desc;
    }
}
