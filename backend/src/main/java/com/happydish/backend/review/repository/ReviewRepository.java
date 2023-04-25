package com.happydish.backend.review.repository;

import com.happydish.backend.item.entity.Item;
import com.happydish.backend.review.entity.Review;
import com.happydish.backend.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    long countAllByUser(User user);
    Page<Review> findByItem(Item item, Pageable pageable);
}
