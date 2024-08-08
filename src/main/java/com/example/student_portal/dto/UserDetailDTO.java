package com.example.student_portal.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailDTO {
    private String id;
    private String fatherName;
    private String motherName;
    private String fatherOccupation;
    private String motherOccupation;
    private String annualIncome;
    private String fatherPhoneNo;
    private String motherPhoneNo;
    private String phoneNo;
    private String department;
    private String userId;
}
