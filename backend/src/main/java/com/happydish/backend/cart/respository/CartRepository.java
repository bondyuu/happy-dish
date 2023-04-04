package com.happydish.backend.cart.respository;

import com.happydish.backend.cart.model.Cart;
import com.happydish.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
