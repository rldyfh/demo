package com.blog.myBlog.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MyBlogException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public MyBlogException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        this.validation.put(fieldName, message);
    }



}
