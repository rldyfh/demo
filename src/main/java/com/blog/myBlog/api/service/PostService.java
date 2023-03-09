package com.blog.myBlog.api.service;


import com.blog.myBlog.api.config.auth.dto.SessionUser;
import com.blog.myBlog.api.domain.Post;
import com.blog.myBlog.api.domain.Role;
import com.blog.myBlog.api.domain.Users;
import com.blog.myBlog.api.exception.DeleteException;
import com.blog.myBlog.api.exception.PostNotFound;
import com.blog.myBlog.api.repository.PostRepository;
import com.blog.myBlog.api.repository.UserRepository;
import com.blog.myBlog.api.request.PostCreate;
import com.blog.myBlog.api.request.PostEdit;
import com.blog.myBlog.api.response.CommentResponse;
import com.blog.myBlog.api.response.LoginUser;
import com.blog.myBlog.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void write(PostCreate postCreate, SessionUser user) {

        Post post = Post.builder()
                        .title((postCreate.getTitle()))
                                .content(postCreate.getContent())
                                        .build();

        Optional<Users> user1 = userRepository.findByEmail(user.getEmail());
        post.setUser(user1.get());
        postRepository.save(post);
    }

    public PostResponse get(Long id, SessionUser user) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);


        List<CommentResponse> commentResponses = post.getCommentList().stream()
                .map(c -> new CommentResponse(c.getId(), c.getContent(), new LoginUser(user.getName()))).toList();

        PostResponse response = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getName())
                .commentList(commentResponses)
                .build();


        return response;
    }

    // entity(Post) 가 그대로 리턴되는거 원치 않음
    // PostResponse로 response하자
    // builder 코드의 중복 우려 -> 생성자 오버로딩
    public Page<PostResponse> getList(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> new PostResponse(post));
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        post.change(postEdit.getTitle(), postEdit.getContent());
        postRepository.save(post);

    }

    public void delete(Long id, SessionUser user) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        if(post.getUser().getName().equals(user.getName())) {
            postRepository.delete(post);
        }else {
            throw new DeleteException();
        }

    }
}
