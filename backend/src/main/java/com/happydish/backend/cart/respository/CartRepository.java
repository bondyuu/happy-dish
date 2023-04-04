package com.happydish.backend.cart.respository;

import com.happydish.backend.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
