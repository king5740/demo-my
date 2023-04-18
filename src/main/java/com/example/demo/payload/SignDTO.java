package com.example.demo.payload;


import com.example.demo.util.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class SignDTO {

    @NotBlank(message = MessageConstants.MUST_NOT_BE_BLANK_EMAIL)
    private String name;

    @NotBlank(message = MessageConstants.MUST_NOT_BE_BLANK_PASSWORD)
    private String password;
}
