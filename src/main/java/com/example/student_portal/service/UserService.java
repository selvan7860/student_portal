package com.example.student_portal.service;

import com.example.student_portal.dao.User;
import com.example.student_portal.dto.UserDTO;
import com.example.student_portal.exception.CustomException;
import com.example.student_portal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        Optional<User> users = userRepository.findByEmail(userDTO.getEmail());
        if(users.isPresent()){
            throw new CustomException("Email Already Exists", HttpStatus.BAD_REQUEST);
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        User saveUser = userRepository.save(user);
        return convertDTO(saveUser);
    }

    private UserDTO convertDTO(User saveUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(saveUser.getId());
        userDTO.setName(saveUser.getName());
        userDTO.setEmail(saveUser.getEmail());
        userDTO.setPassword(saveUser.getPassword());
        return userDTO;
    }
}
