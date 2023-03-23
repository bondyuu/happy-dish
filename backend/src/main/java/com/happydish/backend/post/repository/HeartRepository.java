package com.happydish.backend.post.repository;

import com.happydish.backend.post.model.Heart;
import com.happydish.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    long countAllByUser(User user);
}
