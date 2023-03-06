package com.blog.myBlog.api.response;

import com.blog.myBlog.api.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private List<CommentResponse> commentList;


    public PostResponse(Post post){
        this.id= post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

    }

    @Builder
    public PostResponse(Long id, String title, String content, String author, List<CommentResponse> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.commentList = commentList;
    }


}
