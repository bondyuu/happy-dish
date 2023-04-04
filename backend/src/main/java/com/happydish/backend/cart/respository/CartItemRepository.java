package com.happydish.backend.cart.respository;

import com.happydish.backend.cart.model.Cart;
import com.happydish.backend.cart.model.CartItem;
import com.happydish.backend.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);
}
