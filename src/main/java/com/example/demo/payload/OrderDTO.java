package com.example.demo.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderDTO {

    long orderId;
    String firstName;
    String lastName;
    long numberOfOrders;
    String phoneNumber;
    String address;
    LocalDateTime orderDate;
}
