package com.blog.myBlog.api.domain;

import com.blog.myBlog.api.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void BaseTimeEntity_Test() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postRepository.save(Post.builder()
                .title("제목")
                .content("내용")
                .build());

        //when
        List<Post> postsList = postRepository.findAll();

        //then
        Post post = postsList.get(0);
        assertNotEquals(post.getCreatedDate(), LocalDateTime.now());


    }


}