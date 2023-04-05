package com.happydish.backend.review.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.review.dto.ReviewRequestDto;
import com.happydish.backend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/save/{itemId}")
    public ResponseEntity<?> saveReview(@RequestBody ReviewRequestDto requestDto,
                                        @AuthenticationPrincipal PrincipleDetails principleDetails,
                                        @PathVariable(name = "itemId") long id) {
        return reviewService.saveReview(requestDto, principleDetails, id);
    }
}
