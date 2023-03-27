package com.happydish.backend.user.repository;

import com.happydish.backend.user.model.RefreshToken;
import com.happydish.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);
}
