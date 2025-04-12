package com.cdsg.coursemanagement.service;

import com.cdsg.coursemanagement.dto.UserRegisterRequest;
import com.cdsg.coursemanagement.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findUserByUserName(String userName);
    int registerUser(UserRegisterRequest registerRequest);

}
