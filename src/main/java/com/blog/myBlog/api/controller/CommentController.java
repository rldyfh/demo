package com.blog.myBlog.api.controller;


import com.blog.myBlog.api.config.auth.dto.SessionUser;
import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Role;
import com.blog.myBlog.api.domain.Users;
import com.blog.myBlog.api.repository.PostRepository;
import com.blog.myBlog.api.repository.UserRepository;
import com.blog.myBlog.api.request.CommentRequest;
import com.blog.myBlog.api.request.PostCreate;
import com.blog.myBlog.api.request.PostEdit;
import com.blog.myBlog.api.response.CommentResponse;
import com.blog.myBlog.api.response.PostResponse;
import com.blog.myBlog.api.service.CommentService;
import com.blog.myBlog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentService commentService;


    @PostMapping("/{postId}/comment")
    public String saveComment(@ModelAttribute CommentRequest commentRequest, @PathVariable Long postId,
                              @RequestParam String page, RedirectAttributes re, @SessionAttribute(name = "user") SessionUser user) {

        log.info("comment coming");
        commentRequest.setPostId(postId);


        re.addAttribute("page", page);
        commentService.commentSave(commentRequest, user);
        return "redirect:/posts/" + postId;
    }


//    @GetMapping("/comment")
//    public List<CommentResponse> getComment() {
//        return commentService.getList();
//
//    }



    /**
     *  더미 데이터
     */
    @PostConstruct
    public void initializing() {

        //기본 admin
        Users admin = userRepository.save(Users.builder().id(1L).name("ADMIN").email("ADMIN@google.com").role(Role.ADMIN).build());

        for (int i = 0; i < 115; i++) {
            PostCreate post = PostCreate.builder()
                    .title("title" + i)
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
