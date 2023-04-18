package com.example.demo.controller;

import com.example.demo.payload.ApiResult;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.TokenDTO;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<TokenDTO> signIn(@Valid SignDTO signDTO) {
        return authService.signIn(signDTO);
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        return authService.refreshToken(accessToken, refreshToken);
    }

}
