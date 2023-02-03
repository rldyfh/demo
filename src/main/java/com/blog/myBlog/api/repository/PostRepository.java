package com.blog.myBlog.api.repository;

import com.blog.myBlog.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
