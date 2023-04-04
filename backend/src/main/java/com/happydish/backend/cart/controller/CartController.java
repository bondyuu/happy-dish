package com.happydish.backend.cart.controller;

import com.happydish.backend.cart.service.CartService;
import com.happydish.backend.global.auth.PrincipleDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/{itemId}/add")
    public ResponseEntity<?> addItem(@PathVariable(name = "itemId") long id,
                                     @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return cartService.addItem(id, principleDetails);
    }

    @PostMapping("/{itemId}/delete")
    public ResponseEntity<?> delItem(@PathVariable(name = "itemId") long id,
                                     @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return cartService.delItem(id, principleDetails);
    }
}
