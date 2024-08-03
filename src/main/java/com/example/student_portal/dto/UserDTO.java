package com.example.student_portal.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
}
