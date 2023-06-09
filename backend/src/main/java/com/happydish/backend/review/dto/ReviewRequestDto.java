package com.happydish.backend.review.dto;

import com.happydish.backend.item.entity.Item;
import com.happydish.backend.review.entity.Review;
import com.happydish.backend.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private String title;
    private String content;

    public Review toEntity(User user, Item item) {
        return Review.builder()
                     .title(this.title)
                     .content(this.content)
                     .user(user)
                     .item(item)
                     .build();
    }
}
