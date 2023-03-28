package com.happydish.backend.item.repository;

import com.happydish.backend.item.model.Item;
import com.happydish.backend.item.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByStatus(Status status, Pageable pageable);
    Page<Item> findAllByTitleContainingAndStatus(String title, Status status, Pageable pageable);
}
