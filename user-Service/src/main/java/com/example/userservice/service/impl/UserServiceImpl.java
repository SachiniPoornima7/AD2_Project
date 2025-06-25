package com.example.userservice.service.impl;


import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.repo.UserRepository;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return "User registered successfully with ID: " + user.getId();
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> modelMapper.map(value, UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? modelMapper.map(user, UserDTO.class) : null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getId())) {
            User user = modelMapper.map(userDTO, User.class);
            userRepository.save(user);
            return userDTO;
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}