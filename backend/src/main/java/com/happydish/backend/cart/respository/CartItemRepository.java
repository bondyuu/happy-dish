package com.happydish.backend.cart.respository;

import com.happydish.backend.cart.entity.Cart;
import com.happydish.backend.cart.entity.CartItem;
import com.happydish.backend.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);
}
