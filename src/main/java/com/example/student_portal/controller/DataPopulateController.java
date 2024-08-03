package com.example.student_portal.controller;



import com.example.student_portal.service.DataPopulateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/data")
public class DataPopulateController {

    private final DataPopulateService dataPopulateService;

    public DataPopulateController(DataPopulateService dataPopulateService) {
        this.dataPopulateService = dataPopulateService;
    }

    @PutMapping("/semester")
    public ResponseEntity<?> addSemester(@RequestBody MultipartFile file) throws Exception{
        return new ResponseEntity<>(dataPopulateService.addSemester(file), HttpStatus.OK);
    }

    @PutMapping("/subject ")
    public ResponseEntity<?> addSubject(@RequestBody MultipartFile file) throws Exception{
        return new ResponseEntity<>(dataPopulateService.addSubject(file), HttpStatus.OK);
    }
}
