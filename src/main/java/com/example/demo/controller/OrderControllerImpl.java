package com.example.demo.controller;


import com.example.demo.payload.ApiResult;
import com.example.demo.payload.OrderAddDTO;
import com.example.demo.payload.OrderCoverDTO;
import com.example.demo.payload.OrderDTO;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController{
    private final OrderService orderService;

    @Override
    public ApiResult<OrderDTO> add(OrderAddDTO orderAddDTO) {
        return orderService.add(orderAddDTO);
    }

    @Override
    public ApiResult<OrderCoverDTO> getAll() {
        return orderService.getAll();
    }

    @Override
    public ApiResult<?> delete(Long orderId) {
        return orderService.delete(orderId);
    }
}
