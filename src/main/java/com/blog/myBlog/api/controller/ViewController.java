package com.blog.myBlog.api.controller;

import com.blog.myBlog.api.response.PostResponse;
import com.blog.myBlog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        return "home";
    }



}
