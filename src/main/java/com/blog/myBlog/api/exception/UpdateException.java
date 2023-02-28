package com.blog.myBlog.api.exception;

import java.util.Map;

public class UpdateException extends MyBlogException {

    private static final String MESSAGE = "수정할 권한이 없습니다.";

    public UpdateException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
