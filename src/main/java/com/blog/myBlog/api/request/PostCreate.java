package com.blog.myBlog.api.request;

import com.blog.myBlog.api.domain.Post;
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

//    public void validate() {
//        if(this.title.contains("바보")) {
//            throw new InvalidRequest("title", "제목에 바보가 들어갈 수 없습니다.");
//        }


}
