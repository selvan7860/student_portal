package com.example.student_portal.service;

import com.example.student_portal.dao.User;
import com.example.student_portal.dto.UserDTO;
import com.example.student_portal.exception.CustomException;
import com.example.student_portal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String EMAIL_ALREADY_EXISTS = "email already exists";
    private static final String PASSWORD_NOT_MATCH = "password not matched";
    private static final String FILL_ALL_MANDATORY_FIELD = "fill all mandatory field";
    private static final String FILL_EMAIL = "fill your email field";
    private static final String EMAIL_VALIDATION = "invalid email";
    private static final String FILL_PASSWORD = "fill your password";

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        validationUserDTO(userDTO);
        Optional<User> users = userRepository.findByEmail(userDTO.getEmail());
        if(users.isPresent()){
            throw new CustomException(EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(userDTO.getConfirmPassword()));
        user.setRole(userDTO.getRole());
        User saveUser = userRepository.save(user);
        return convertDTO(saveUser);
    }

    private void validationUserDTO(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String confirmPassword = userDTO.getConfirmPassword();
        if(email.isEmpty() || email.trim().isEmpty()){
            throw new CustomException(FILL_EMAIL, HttpStatus.BAD_GATEWAY);
        }
        if(!isValidationEmail(email)){
            throw new CustomException(EMAIL_VALIDATION, HttpStatus.BAD_REQUEST);
        }
        if(password.trim().isEmpty() || confirmPassword.trim().isEmpty()){
            throw new CustomException(FILL_PASSWORD, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isValidationEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private UserDTO convertDTO(User saveUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(saveUser.getId());
        userDTO.setName(saveUser.getName());
        userDTO.setEmail(saveUser.getEmail());
        userDTO.setPassword(saveUser.getPassword());
        userDTO.setConfirmPassword(saveUser.getConfirmPassword());
        userDTO.setRole(saveUser.getRole());
        return userDTO;
    }
}
