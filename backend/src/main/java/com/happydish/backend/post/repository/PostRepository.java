package com.happydish.backend.post.repository;

import com.happydish.backend.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
