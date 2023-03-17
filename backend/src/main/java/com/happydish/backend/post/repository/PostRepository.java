package com.happydish.backend.post.repository;

import com.happydish.backend.post.model.Post;
import com.happydish.backend.post.model.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByStatus(PostStatus status, Pageable pageable);
    Page<Post> findAllByTitleContainingAndStatus(String title, PostStatus status, Pageable pageable);
}
