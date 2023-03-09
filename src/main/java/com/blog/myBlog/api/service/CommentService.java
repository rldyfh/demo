package com.blog.myBlog.api.service;

import com.blog.myBlog.api.config.auth.dto.SessionUser;
import com.blog.myBlog.api.domain.Comment;
import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Users;
import com.blog.myBlog.api.repository.CommentRepository;
import com.blog.myBlog.api.repository.PostRepository;
import com.blog.myBlog.api.repository.UserRepository;
import com.blog.myBlog.api.request.CommentRequest;
import com.blog.myBlog.api.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public void commentSave(CommentRequest commentRequest, SessionUser user) {

        Post post = postRepository.findById(commentRequest.getPostId()).get();
        Users findUser = userRepository.findByEmail(user.getEmail()).get();

        Comment comment = Comment.builder()
                .parent(commentRequest.getParent())
                .content(commentRequest.getContent())
                .post(post)
                .user(findUser)
                .build();

        commentRepository.save(comment);

    }

//    public List<CommentResponse> getList() {
//        List<Comment> commentList = commentRepository.findAll();
//
//        return commentList.stream().map(c -> new CommentResponse(c.getId(), c.getContent())).toList();
//    }
}
