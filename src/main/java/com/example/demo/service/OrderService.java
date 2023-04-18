package com.example.demo.service;

import com.example.demo.payload.ApiResult;
import com.example.demo.payload.OrderAddDTO;
import com.example.demo.payload.OrderCoverDTO;
import com.example.demo.payload.OrderDTO;

public interface OrderService {
    ApiResult<OrderDTO> add(OrderAddDTO orderAddDTO);

    ApiResult<OrderCoverDTO> getAll();

    ApiResult<?> delete(Long orderId);
}
