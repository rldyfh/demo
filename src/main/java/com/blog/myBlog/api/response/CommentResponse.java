package com.blog.myBlog.api.response;


import com.blog.myBlog.api.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {


    private Long id;
    private String content;
    private LoginUser user;

    public CommentResponse(Long id, String content, LoginUser user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }


}
