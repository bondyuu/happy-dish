package com.happydish.backend.order.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.item.model.Item;
import com.happydish.backend.item.repository.ItemRepository;
import com.happydish.backend.order.dto.OrderRequestDto;
import com.happydish.backend.order.model.Order;
import com.happydish.backend.order.model.OrderItem;
import com.happydish.backend.order.repository.OrderItemRepository;
import com.happydish.backend.order.repository.OrderRepository;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public ResponseEntity<?> saveOrder(OrderRequestDto requestDto, PrincipleDetails principleDetails){
        Optional<User> optionalUser = userRepository.findById(principleDetails.getUser().getId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }
        User user = optionalUser.get();

        Order order = orderRepository.save(Order.builder().user(user).build());

        List<Map<String, Long>> list = requestDto.getList();
        for (Map<String,Long> map: list) {
            long id = map.get("id");

            Optional<Item> optionalItem = itemRepository.findById(id);
            if (optionalItem.isEmpty()) {
                return ResponseEntity.badRequest().body("Item Not Found: id is " + id);
            }
            Item item = optionalItem.get();

            orderItemRepository.save(OrderItem.builder()
                                                .item(item)
                                                .order(order)
                                                .build());
        }

        return ResponseEntity.ok("ok");
    }
}
