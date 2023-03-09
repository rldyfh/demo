package com.blog.myBlog.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    private String content;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne
    private Post post;

    @ManyToOne
    private Users user;


    @Builder
    public Comment(Long id, Comment parent, String content,  Post post, Users user) {
        this.id = id;
        this.parent = parent;
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public void setPostComment(Post post) {
        this.post = post;
        post.getCommentList().add(this);
    }

    public void setParentComment(Comment comment) {
        this.parent = comment;
        comment.getChildren().add(this);
    }


}
