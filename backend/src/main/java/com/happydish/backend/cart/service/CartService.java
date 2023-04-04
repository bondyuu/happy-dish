package com.happydish.backend.cart.service;

import com.happydish.backend.cart.model.Cart;
import com.happydish.backend.cart.model.CartItem;
import com.happydish.backend.cart.respository.CartItemRepository;
import com.happydish.backend.cart.respository.CartRepository;
import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.item.model.Item;
import com.happydish.backend.item.repository.ItemRepository;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public ResponseEntity<?> addItem(long id, PrincipleDetails principleDetails) {
        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }
        User loginUser = optionalUser.get();

        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isEmpty()) {
            return ResponseEntity.badRequest().body("Item Not Found");
        }

        Item item = optionalItem.get();

        Cart cart = cartRepository.findByUser(loginUser);

        cartItemRepository.save(CartItem.builder()
                                        .cart(cart)
                                        .item(item)
                                        .build());

        return ResponseEntity.ok("ok");
    }

    @Transactional
    public ResponseEntity<?> delItem(long id, PrincipleDetails principleDetails) {
        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }
        User loginUser = optionalUser.get();
        Cart cart = cartRepository.findByUser(loginUser);

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            return ResponseEntity.badRequest().body("Item Not Found");
        }
        Item item = optionalItem.get();

        Optional<CartItem> optionalCartItem = cartItemRepository.findByCartAndItem(cart, item);
        if(optionalCartItem.isEmpty()) {
            return ResponseEntity.badRequest().body("Item Not Found");
        }
        CartItem cartItem = optionalCartItem.get();

        cartItemRepository.delete(cartItem);

        return ResponseEntity.ok("ok");
    }
}
