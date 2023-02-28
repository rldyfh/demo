package com.blog.myBlog.api.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class DeleteException extends MyBlogException{

    private static final String MESSAGE ="삭제할 권한이 없습니다.";

    public DeleteException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
