package com.cdsg.coursemanagement.service.impl;

import com.cdsg.coursemanagement.dto.UserRegisterRequest;
import com.cdsg.coursemanagement.dto.UserDTO;
import com.cdsg.coursemanagement.enums.UserRole;
import com.cdsg.coursemanagement.service.UserService;
import com.cdsg.coursemanagement.repository.UserRepository;
import com.cdsg.coursemanagement.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Optional<UserDTO> findUserByUserName(String userName) {
        return Optional.ofNullable(userRepository.findUserByUserName(userName))
                .map(user -> new UserDTO(user.getId(), user.getUserName(), user.getRole().toString()));
    }

    @Override
    public int registerUser(UserRegisterRequest registerRequest) {
        try {
            User user = User.builder().userName(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(UserRole.valueOf(registerRequest.getRole().toUpperCase()))
                    .build();
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}