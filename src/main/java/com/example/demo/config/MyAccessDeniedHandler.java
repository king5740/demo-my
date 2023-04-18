package com.example.demo.config;

import com.example.demo.exceptions.ExceptionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private final ExceptionHelper exceptionHelper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        System.out.println("___ ".repeat(8));
        ResponseEntity<?> responseEntity = exceptionHelper.handleException(accessDeniedException);
        System.out.println(responseEntity);
    }
}
