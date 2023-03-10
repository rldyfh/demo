package com.blog.myBlog.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private Users user;

    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    //연관관계 메소드
    public void setUser(Users user) {
        this.user = user;
        user.getPostList().add(this);
    }


    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;

    }
}
