package com.example.demo.controller;


import com.example.demo.payload.ApiResult;
import com.example.demo.payload.OrderAddDTO;
import com.example.demo.payload.OrderCoverDTO;
import com.example.demo.payload.OrderDTO;
import com.example.demo.util.RestConstants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(OrderController.ADDRESS_BASE_PATH)
public interface OrderController {

    String ADDRESS_BASE_PATH = RestConstants.BASE_PATH+"order";

    @PostMapping("/add")
    ApiResult<OrderDTO> add(@RequestBody @Valid OrderAddDTO orderAddDTO);

    @GetMapping("/get-all")
    ApiResult<OrderCoverDTO> getAll();

    @DeleteMapping("{order-id}")
    ApiResult<?> delete(@PathVariable("order-id") Long orderId);

}
