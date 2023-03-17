package com.happydish.backend.post.repository;

import com.happydish.backend.post.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
