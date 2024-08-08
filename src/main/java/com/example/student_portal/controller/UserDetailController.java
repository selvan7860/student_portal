package com.example.student_portal.controller;


import com.example.student_portal.dto.GenericResponse;
import com.example.student_portal.dto.UserDetailDTO;
import com.example.student_portal.service.UserDetailService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/userDetails")
public class UserDetailController {

    private final UserDetailService userDetailService;

    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping
    public GenericResponse addUserDetails(@RequestBody UserDetailDTO userDetailDTO){
        return new GenericResponse(userDetailService.addUserDetail(userDetailDTO));
    }

    @PutMapping("/{id}")
    public GenericResponse updateUserDetails(@PathVariable String id,@RequestBody UserDetailDTO userDetailDTO){
        return new GenericResponse(userDetailService.updateUserDetail(id,userDetailDTO));
    }
}

