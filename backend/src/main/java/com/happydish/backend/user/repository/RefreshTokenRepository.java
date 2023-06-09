package com.happydish.backend.user.repository;

import com.happydish.backend.user.entity.RefreshToken;
import com.happydish.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);
}
