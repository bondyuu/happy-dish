package com.happydish.backend.item.entity;

import com.happydish.backend.cart.entity.CartItem;
import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.item.dto.EditRequestDto;
import com.happydish.backend.item.dto.ItemDetailDto;
import com.happydish.backend.item.dto.ItemDto;
import com.happydish.backend.review.entity.Review;
import com.happydish.backend.user.entity.Role;
import com.happydish.backend.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private String imageUrl;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    private List<CartItem> cartList = new ArrayList<>();

    @Builder
    public Item(String title, String content, User user, String url) {
        this.title = title;
        this.content = content;
        this.status = Status.ACTIVE;
        this.imageUrl = url;
    }

    public void deletedBy(Role role) {
        if (role.equals(Role.ROLE_ADMIN)) {
            this.status = Status.ADMIN_DELETED;
        } else {
            this.status = Status.USER_DELETED;
        }
    }

    public void edit(EditRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        if (!requestDto.getImageUrl().equals("")) {
            this.imageUrl = requestDto.getImageUrl();
        }
    }

    public ItemDto toPostDto() {
        return ItemDto.builder()
                .itemId(this.id)
                .title(this.title)
                .content(this.content)
                .status(this.status.toString())
                .imageUrl(this.imageUrl)
                .reviewNum(this.reviewList.size())
                .createdAt(this.getCreatedAt())
                .build();
    }

    public ItemDetailDto toDetailDtl() {
        return ItemDetailDto.builder()
                .itemId(this.id)
                .title(this.title)
                .content(this.content)
                .status(this.status.toString())
                .imageUrl(this.imageUrl)
                .reviewNum(this.reviewList.size())
                .createdAt(this.getCreatedAt())
                .build();
    }
}
