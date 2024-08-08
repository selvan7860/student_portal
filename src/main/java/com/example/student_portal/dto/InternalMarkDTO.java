package com.example.student_portal.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalMarkDTO {

    private String semesterId;
    private String subjectId;
    private String userId;
    private int mark;
}
