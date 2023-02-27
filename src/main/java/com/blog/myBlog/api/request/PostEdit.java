package com.blog.myBlog.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostEdit {

    private Long id;
    @NotBlank(message = "타이틀을 입력해주세요")
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요")
    private String content;


    @Builder
    public PostEdit(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


}
