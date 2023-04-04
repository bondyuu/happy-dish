package com.happydish.backend.item.repository;

import com.happydish.backend.review.model.Review;
import com.happydish.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    long countAllByUser(User user);
}
