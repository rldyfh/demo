package com.blog.myBlog.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultErrorResponse {

    private final String code;
    private final String message;

    @Builder
    public DefaultErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
