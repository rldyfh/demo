package com.blog.myBlog.api.request;

import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Users;
import com.blog.myBlog.api.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요")
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요")
    private String content;

    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public PostCreate() {

    }

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
