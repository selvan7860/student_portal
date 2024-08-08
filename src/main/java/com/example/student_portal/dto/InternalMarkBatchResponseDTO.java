package com.example.student_portal.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InternalMarkBatchResponseDTO {

    private List<InternalMarkDTO> internalMarkDTOS;
}
