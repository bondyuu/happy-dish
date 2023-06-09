package com.happydish.backend.review.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.item.entity.Item;
import com.happydish.backend.review.repository.ReviewRepository;
import com.happydish.backend.review.dto.ReviewRequestDto;
import com.happydish.backend.review.entity.Review;
import com.happydish.backend.item.repository.ItemRepository;
import com.happydish.backend.user.entity.Role;
import com.happydish.backend.user.entity.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public ResponseEntity<?> saveReview(ReviewRequestDto requestDto, PrincipleDetails principleDetails, long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if(optionalItem.isEmpty()) {
            return ResponseEntity.badRequest().body("Item Not Found");
        }

        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User NotFound");
        }

        Item item = optionalItem.get();
        User user = optionalUser.get();

        Review review = requestDto.toEntity(user, item);

        reviewRepository.save(review);

        return ResponseEntity.ok("ok");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getReviewByItem(long id, Pageable pageable) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            return ResponseEntity.badRequest().body("Item Not Found");
        }
        Item item = optionalItem.get();

        return ResponseEntity.ok(reviewRepository.findByItem(item, pageable));
    }

    @Transactional
    public ResponseEntity<?> delReview(long id, PrincipleDetails principleDetails) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            return ResponseEntity.badRequest().body("Review Not Found");
        }

        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        Review review = optionalReview.get();
        User user = optionalUser.get();

        if (user.canNotControl(review)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        if (user.isAdmin()) {
            review.deletedBy(Role.ROLE_ADMIN);
        } else {
            review.deletedBy(Role.ROLE_USER);
        }

        return ResponseEntity.ok("ok");
    }

    @Transactional
    public ResponseEntity<?> editReview(long id, ReviewRequestDto requestDto, PrincipleDetails principleDetails) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            return ResponseEntity.badRequest().body("Review Not Found");
        }

        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        Review review = optionalReview.get();
        User user = optionalUser.get();

        if (user.canNotControl(review)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        review.edit(requestDto);

        return ResponseEntity.ok("ok");
    }
}
