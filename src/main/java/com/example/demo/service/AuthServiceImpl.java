package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.RestException;
import com.example.demo.payload.ApiResult;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.TokenDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.util.MessageConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository employeeRepository;


    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider,
                           @Lazy AuthenticationManager authenticationManager,
                           UserRepository employeeRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository
                .findFirstByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignDTO signInForEmployeeDTO) {
        User employee;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInForEmployeeDTO.getName(),
                            signInForEmployeeDTO.getPassword()
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            employee = (User) authentication.getPrincipal();

        } catch (DisabledException | LockedException | CredentialsExpiredException disabledException) {
            throw RestException.restThrow(MessageConstants.USER_NOT_FOUND_OR_DISABLED, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException | UsernameNotFoundException badCredentialsException) {
            throw RestException.restThrow(MessageConstants.LOGIN_OR_PASSWORD_ERROR, HttpStatus.UNAUTHORIZED);
        }

        return ApiResult.successResponse(generateTokenDTO(employee), MessageConstants.SUCCESSFULLY_TOKEN_GENERATED);
    }

    @Override
    @Transactional
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        try {
            String userId = jwtTokenProvider.getUserIdFromToken(refreshToken, false);
            User employee = getEmployeeById(Integer.valueOf(userId)).orElseThrow(
                    () -> RestException.restThrow(MessageConstants.TOKEN_INVALID, HttpStatus.UNAUTHORIZED));

            if (!employee.isEnabled()
                    || !employee.isAccountNonExpired()
                    || !employee.isAccountNonLocked()
                    || !employee.isCredentialsNonExpired())
                throw RestException.restThrow(MessageConstants.USER_PERMISSION_RESTRICTION, HttpStatus.UNAUTHORIZED);

            return ApiResult.successResponse(generateTokenDTO(employee));
        } catch (Exception e) {
            throw RestException.restThrow(MessageConstants.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Optional<User> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }


    private TokenDTO generateTokenDTO(User employee) {
        LocalDateTime tokenIssuedAt = LocalDateTime.now();
        String accessToken = jwtTokenProvider.generateAccessToken(employee, Timestamp.valueOf(tokenIssuedAt));
        String refreshToken = jwtTokenProvider.generateRefreshToken(employee);

        employee.setTokenIssuedAt(tokenIssuedAt);
        employeeRepository.save(employee);

        return TokenDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


}
