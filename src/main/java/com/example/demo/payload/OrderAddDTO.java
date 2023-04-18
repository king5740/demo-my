package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddDTO {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotNull
    @Min(1)
    Long numberOfOrders;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String address;
}
