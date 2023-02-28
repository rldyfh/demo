package com.blog.myBlog.api.response;

import com.blog.myBlog.api.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;


    public PostResponse(Post post){
        this.id= post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

    }

    public PostResponse(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
