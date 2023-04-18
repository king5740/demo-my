package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.exceptions.RestException;
import com.example.demo.payload.ApiResult;
import com.example.demo.payload.OrderAddDTO;
import com.example.demo.payload.OrderCoverDTO;
import com.example.demo.payload.OrderDTO;
import com.example.demo.repository.OrderRepository;
import com.example.demo.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

private final OrderRepository orderRepository;

    @Override
    public ApiResult<OrderDTO> add(OrderAddDTO orderAddDTO) {
        Orders order = orderRepository.save(mapOrderAddDTOToOrders(orderAddDTO));

        return ApiResult.successResponse(mapOrderDTOToOrder(order));
    }

    @Override
    public ApiResult<OrderCoverDTO> getAll() {
        AtomicLong total = new AtomicLong(0L);

        List<OrderDTO> orderDTOS = orderRepository.findAll(Sort.by("id").descending())
                .stream()
                .map(order -> {
                    total.addAndGet(order.getNumberOfOrders());
                    return mapOrderDTOToOrder(order);
                }).toList();

        return ApiResult.successResponse(new OrderCoverDTO(total.get(), orderDTOS));
    }

    @Override
    public ApiResult<?> delete(Long orderId) {
        if (!orderRepository.existsById(orderId))
            throw RestException.restThrow(MessageConstants.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND);

        orderRepository.deleteById(orderId);

        return ApiResult.successResponse();
    }


    private Orders mapOrderAddDTOToOrders(OrderAddDTO orderAddDTO){
        return Orders.builder()
                .phoneNumber(orderAddDTO.getPhoneNumber())
                .numberOfOrders(orderAddDTO.getNumberOfOrders())
                .orderDate(LocalDateTime.now())
                .address(orderAddDTO.getAddress())
                .lastName(orderAddDTO.getLastName())
                .firstName(orderAddDTO.getFirstName())
                .build();
    }


    private OrderDTO mapOrderDTOToOrder(Orders order){
        return OrderDTO.builder()
                .orderId(order.getId())
                .phoneNumber(order.getPhoneNumber())
                .numberOfOrders(order.getNumberOfOrders())
                .orderDate(order.getOrderDate())
                .address(order.getAddress())
                .lastName(order.getLastName())
                .firstName(order.getFirstName())
                .build();
    }
}
