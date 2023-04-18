package com.example.demo.controller;

import com.example.demo.payload.ApiResult;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.TokenDTO;
import com.example.demo.util.RestConstants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
public interface AuthController {
    String AUTH_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "auth";
    String SIGN_IN_PATH = "/sign-in";
    String REFRESH_TOKEN_PATH = "/refresh-token";

    @PostMapping(path = SIGN_IN_PATH)
    ApiResult<TokenDTO> signIn(@Valid @RequestBody SignDTO signDTO);

    @GetMapping(path = REFRESH_TOKEN_PATH)
    ApiResult<TokenDTO> refreshToken(@RequestHeader(value = "Authorization") String accessToken,
                                     @RequestHeader(value = "RefreshToken") String refreshToken);


}
