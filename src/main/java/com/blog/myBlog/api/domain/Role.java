package com.blog.myBlog.api.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE", "손님"),
    USER("ROLE_USER", "회원");

    private final String key;
    private final String title;

}
