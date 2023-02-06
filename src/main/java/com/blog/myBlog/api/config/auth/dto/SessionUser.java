package com.blog.myBlog.api.config.auth.dto;

import com.blog.myBlog.api.domain.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
