package com.blog.myBlog.api.response;

import lombok.Getter;

@Getter
public class LoginUser {

    private String name;


    public LoginUser(String name) {
        this.name = name;
    }
}
