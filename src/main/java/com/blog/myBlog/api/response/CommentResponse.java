package com.blog.myBlog.api.response;


import com.blog.myBlog.api.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {


    private Long id;
    private String content;

    public CommentResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }


}
