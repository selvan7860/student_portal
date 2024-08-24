package com.example.student_portal.dao;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("Admin"),
    STUDENT("Student"),
    STAFF("Staff");

    private final String roleName;


    Role(String roleName) {
        this.roleName = roleName;
    }
}
