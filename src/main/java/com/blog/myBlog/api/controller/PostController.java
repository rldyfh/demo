package com.blog.myBlog.api.controller;


import com.blog.myBlog.api.config.auth.dto.SessionUser;
import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Role;
import com.blog.myBlog.api.domain.Users;
import com.blog.myBlog.api.repository.PostRepository;
import com.blog.myBlog.api.repository.UserRepository;
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
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

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

    @GetMapping("/")
    public SessionUser index() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return user;
    }

    /**
     *  더미 데이터
     */
    @PostConstruct
    public void initializing() {

        //기본 admin
        Users admin = userRepository.save(Users.builder().id(1L).name("ADMIN").email("ADMIN@google.com").role(Role.ADMIN).build());

        for (int i = 0; i < 100; i++) {
            PostCreate post = PostCreate.builder()
                    .title("title " + i)
                    .content("content" + i)
                    .build();
            //postService.write(post);

            Post build = Post.builder().title(post.getTitle()).content(post.getContent())
                    .build();

            build.setUser(admin);
            postRepository.save(build);
        }
    }

}
