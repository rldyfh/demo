package com.blog.myBlog.api.config.auth.dto;

import com.blog.myBlog.api.domain.Users;
import lombok.Getter;

@Getter
public class SessionUser {

    private String name;
    private String email;

    public SessionUser(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
