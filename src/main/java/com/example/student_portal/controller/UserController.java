package com.example.student_portal.controller;



import com.example.student_portal.dto.GenericResponse;
import com.example.student_portal.dto.UserDTO;
import com.example.student_portal.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public GenericResponse addUser(@RequestBody UserDTO userDTO){
        return new GenericResponse(userService.addUser(userDTO));
    }
}
