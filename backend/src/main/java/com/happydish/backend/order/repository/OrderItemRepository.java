package com.happydish.backend.order.repository;

import com.happydish.backend.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
