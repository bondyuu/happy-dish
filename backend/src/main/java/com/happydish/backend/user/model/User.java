package com.happydish.backend.user.model;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User {

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

    @Builder
    public User(String email, String name, String password, String provider, String providerId, String profileUrl) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.role = Role.ROLE_USER;
        this.status = UserStatus.ACTIVE;
        this.profileUrl = profileUrl;
    }

    public void editImage(String url) {
        this.profileUrl = url;
    }

    public void editPassword(String password) {
        this.password = password;
    }
}
