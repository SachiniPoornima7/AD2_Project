package com.example.userservice.service;


import com.example.userservice.dto.UserDTO;

public interface UserService {
    String registerUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(Long id);
}
