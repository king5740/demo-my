package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResult;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> signIn(SignDTO signDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken);

    Optional<User> getEmployeeById(Integer id);

}
