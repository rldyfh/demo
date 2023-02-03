package com.blog.myBlog.api.controller;


import com.blog.myBlog.api.request.PostCreate;
import com.blog.myBlog.api.request.PostEdit;
import com.blog.myBlog.api.response.PostResponse;
import com.blog.myBlog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Validated PostCreate postCreate) {
        postService.write(postCreate);
    }

    //글 1개 조회
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    //글 여러개 조회
    @GetMapping("/posts")
    public List<PostResponse> getList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getList(pageable);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    /**
     *  더미 데이터
     */
    @PostConstruct
    public void initializing() {
        for (int i = 0; i < 100; i++) {
            PostCreate post = PostCreate.builder()
                    .title("title " + i)
                    .content("content" + i)
                    .build();
            postService.write(post);
        }
    }

}
