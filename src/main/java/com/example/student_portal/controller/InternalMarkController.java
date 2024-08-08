package com.example.student_portal.controller;


import com.example.student_portal.dto.GenericResponse;
import com.example.student_portal.dto.InternalMarkBatchResponseDTO;
import com.example.student_portal.dto.InternalMarkDTO;
import com.example.student_portal.service.InternalMarkService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/internalMark")
public class InternalMarkController {

    private final InternalMarkService internalMarkService;

    public InternalMarkController(InternalMarkService internalMarkService) {
        this.internalMarkService = internalMarkService;
    }

    @PostMapping
    public GenericResponse addInternalMark(@RequestBody InternalMarkBatchResponseDTO internalMarkBatchResponseDTO){
        System.out.println(internalMarkBatchResponseDTO);
        return new GenericResponse(internalMarkService.addInternalMarks(internalMarkBatchResponseDTO));
    }


}
