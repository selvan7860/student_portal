package com.example.student_portal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalMarkResponseDTO {

    private String userId;
    private String semesterNo;
    private String subjectName;
    private int mark;
}
