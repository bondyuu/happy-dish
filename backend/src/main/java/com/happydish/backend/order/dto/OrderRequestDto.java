package com.happydish.backend.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class OrderRequestDto {
    private List<Map<String, Long>> list;
}
