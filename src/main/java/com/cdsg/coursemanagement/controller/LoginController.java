package com.cdsg.coursemanagement.controller;

import com.cdsg.coursemanagement.dto.LoginRequest;
import com.cdsg.coursemanagement.dto.LoginResponse;
import com.cdsg.coursemanagement.dto.UserRegisterRequest;
import com.cdsg.coursemanagement.dto.UserDTO;
import com.cdsg.coursemanagement.enums.UserRole;
import com.cdsg.coursemanagement.security.JwtUtil;
import com.cdsg.coursemanagement.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest authRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Optional<UserDTO> userDTO = userService.findUserByUserName(authRequest.getUsername());
            String token = null;
            if(userDTO.isPresent()) {
                token = jwtUtil.generateToken(authRequest.getUsername(), userDTO.get().getRole());
            }
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid user name or password.");
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (!isValidUserRole(registerRequest.getRole())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid user role.");
        }
        Optional<UserDTO> userDTO = userService.findUserByUserName(registerRequest.getUsername());
        if (userDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already existed.");
        }
        int result = userService.registerUser(registerRequest);
        if (result == 1) {
            return ResponseEntity.ok("User is registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User registration is failed.");
        }
    }

    private boolean isValidUserRole(String role) {
        try {
            UserRole.valueOf(role.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}