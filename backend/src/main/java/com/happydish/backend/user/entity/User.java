package com.happydish.backend.user.entity;

import com.happydish.backend.cart.entity.Cart;
import com.happydish.backend.global.util.Timestamped;
import com.happydish.backend.review.entity.Review;
import com.happydish.backend.item.entity.Item;
import com.happydish.backend.user.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User extends Timestamped {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String password;

    @Column
    private String provider;

    @Column
    private String providerId;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    private String profileUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @Builder
    public User(String email, String name, String password, String provider, String providerId, String profileUrl, Cart cart) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.role = Role.ROLE_USER;
        this.status = UserStatus.ACTIVE;
        this.profileUrl = profileUrl;
        this.cart = cart;
    }

    public User(String email) {
        this.email = email;
        this.role = Role.ROLE_USER;
    }

    public void editImage(String url) {
        this.profileUrl = url;
    }

    public void editPassword(String password) {
        this.password = password;
    }

    public boolean canNotControl(User target) {
        return target.getId() != this.id && !this.role.equals(Role.ROLE_ADMIN);
    }
    public boolean canNotControl(Review target) {
        return target.getUser() != this && !this.role.equals(Role.ROLE_ADMIN);
    }

    public boolean canNotControl(Item target) {
        return !this.role.equals(Role.ROLE_ADMIN);
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ROLE_ADMIN);
    }

    public void deletedBy(Role role) {
        if (role.equals(Role.ROLE_ADMIN)) {
            this.status = UserStatus.BANNED;
        } else {
            this.status = UserStatus.DELETED;
        }
    }

    public UserDto toUserDto() {
        return UserDto.builder().email(this.email).name(this.name).profileUrl(this.profileUrl).build();
    }
}
