package com.blog.myBlog.api.request;

import com.blog.myBlog.api.domain.Comment;
import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {

    private String content;
    private Long postId;
    private Comment parent;


    public CommentRequest(String content, Long postId, Comment parent) {
        this.content = content;
        this.postId = postId;
        this.parent = parent;
    }
}
