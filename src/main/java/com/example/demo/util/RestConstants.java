package com.example.demo.util;


import com.example.demo.controller.AuthController;
import com.example.demo.controller.OrderController;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {

    ObjectMapper objectMapper = new ObjectMapper();

    String AUTHENTICATION_HEADER = "Authorization";

    String[] OPEN_PAGES = {
            "/*",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
            OrderController.ADDRESS_BASE_PATH+"/*"
    };
    String BASE_PATH = "/api/v1/";

}
