package com.blog.myBlog.api.controller;

import com.blog.myBlog.api.request.PostEdit;
import com.blog.myBlog.api.response.PostResponse;
import com.blog.myBlog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ViewController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        Page<PostResponse> posts = postService.getList(pageable);
        model.addAttribute("posts", posts);

        double start = Math.floor((posts.getNumber()/10)*10 + 1);
        double last = start + 9 < posts.getTotalPages() ? start + 9 : posts.getTotalPages();
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        return "home";
    }

    //글 1개 조회
    @GetMapping("/posts/{postId}")
    public String get(@PathVariable Long postId, @RequestParam int page, Model model) {
        PostResponse post = postService.get(postId);
        model.addAttribute("post", post);
        model.addAttribute("page", page);

        return "post";
    }

    @GetMapping("/posts/{postId}/edit")
    public String editForm(@PathVariable Long postId, @RequestParam int page, Model model) {
        PostResponse post = postService.get(postId);
        model.addAttribute("post", post);
        model.addAttribute("page", page);

        return "editForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String edit(@PathVariable Long postId, @RequestParam int page, RedirectAttributes re, @Validated @ModelAttribute PostEdit postEdit) {
        postService.edit(postId, postEdit);

        re.addAttribute("page", page+1);
        return "redirect:/posts/" + postId;
    }




}
