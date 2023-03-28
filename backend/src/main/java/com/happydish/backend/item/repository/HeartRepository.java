package com.happydish.backend.item.repository;

import com.happydish.backend.item.model.Heart;
import com.happydish.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    long countAllByUser(User user);
}
