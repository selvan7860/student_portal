package com.example.student_portal.dao;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
public class UserDetail {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
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

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
