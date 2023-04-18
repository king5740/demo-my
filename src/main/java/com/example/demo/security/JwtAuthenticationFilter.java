package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.exceptions.RestException;
import com.example.demo.service.AuthService;
import com.example.demo.util.MessageConstants;
import com.example.demo.util.RestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, @Lazy AuthService authService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            setUserPrincipalIfAllOk(httpServletRequest);
        } catch (Exception e) {
            log.error("Error in JwtAuthenticationFilter setUserPrincipalIfAllOk method: ", e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setUserPrincipalIfAllOk(HttpServletRequest request) {
        String authorization = request.getHeader(RestConstants.AUTHENTICATION_HEADER);

        if (authorization != null) {
            User user = getUserFromBearerToken(authorization);
            if (user != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, user.getAuthorities(),null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }

    public User getUserFromBearerToken(String token) {
        try {
            token = token.substring("Bearer".length()).trim();
            if (jwtTokenProvider.isValidToken(token, true)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token, true);
                    return authService.getEmployeeById(Integer.valueOf(userId)).orElseThrow(
                            ()-> RestException.restThrow(MessageConstants.FULL_AUTHENTICATION_REQUIRED, HttpStatus.FORBIDDEN));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
