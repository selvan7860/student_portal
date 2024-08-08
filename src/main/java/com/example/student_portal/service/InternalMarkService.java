package com.example.student_portal.service;

import com.example.student_portal.dao.InternalMark;
import com.example.student_portal.dao.Semester;
import com.example.student_portal.dao.Subject;
import com.example.student_portal.dao.User;
import com.example.student_portal.dto.InternalMarkBatchResponseDTO;
import com.example.student_portal.dto.InternalMarkDTO;
import com.example.student_portal.dto.InternalMarkResponseDTO;
import com.example.student_portal.exception.CustomException;
import com.example.student_portal.repository.InternalMarkRepository;
import com.example.student_portal.repository.SemesterRepository;
import com.example.student_portal.repository.SubjectRepository;
import com.example.student_portal.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InternalMarkService {

    private static final String SEMESTER_NOT_FOUND = "Semester not found";
    private static final String SUBJECT_NOT_FOUND = "Subject not found";
    private static final String INTERNAL_MARK_FOR_SUBJECT_ALREADY_GIVEN_TO_THE_USER = "Already mark given to the subject for this user";

    private final InternalMarkRepository internalMarkRepository;
    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final UserDetailService userDetailService;


    public InternalMarkService(InternalMarkRepository internalMarkRepository, UserRepository userRepository, SemesterRepository semesterRepository, SubjectRepository subjectRepository, UserDetailService userDetailService) {
        this.internalMarkRepository = internalMarkRepository;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
        this.subjectRepository = subjectRepository;
        this.userDetailService = userDetailService;
    }


    public List<InternalMarkResponseDTO> addInternalMarks(InternalMarkBatchResponseDTO internalMarkBatchResponseDTO) {
        List<InternalMarkResponseDTO> responseDTOList = new ArrayList<>();
        for(InternalMarkDTO internalMarkDTOs : internalMarkBatchResponseDTO.getInternalMarkDTOS()){
            String userId = internalMarkDTOs.getUserId();
            String semesterId = internalMarkDTOs.getSemesterId();
            String subjectId = internalMarkDTOs.getSubjectId();
            userDetailService.validationForUser(userId);
            validationForAlreadyExistsInternalMark(userId, subjectId, semesterId);
            validationForSemester(semesterId);
            validationForSemesterAndSubject(semesterId, subjectId);
            InternalMark internalMark = new InternalMark();
            setValueToInternalMark(userId, subjectId, internalMarkDTOs, internalMark);
            InternalMark saveInternalMark = internalMarkRepository.save(internalMark);
            //return convertDTO(saveInternalMark);
            responseDTOList.add(convertDTO(saveInternalMark));
        }
        return responseDTOList;
    }

    private void validationForAlreadyExistsInternalMark(String userId, String subjectId, String semesterId) {
        Optional<InternalMark> existingInternalMark = internalMarkRepository.findByUserIdAndSubjectIdAndSubject_Semester_Id(userId, subjectId, semesterId);
        if(!ObjectUtils.isEmpty(existingInternalMark)){
            throw new CustomException(INTERNAL_MARK_FOR_SUBJECT_ALREADY_GIVEN_TO_THE_USER, HttpStatus.BAD_REQUEST);
        }
    }

    private InternalMarkResponseDTO convertDTO(InternalMark saveInternalMark) {
        InternalMarkResponseDTO internalMarkResponseDTO = new InternalMarkResponseDTO();
        internalMarkResponseDTO.setUserId(saveInternalMark.getUser().getId());
        internalMarkResponseDTO.setSemesterNo(saveInternalMark.getSubject().getSemester().getSemesterNo());
        internalMarkResponseDTO.setSubjectName(saveInternalMark.getSubject().getSubjectName());
        internalMarkResponseDTO.setMark(saveInternalMark.getMark());
        return internalMarkResponseDTO;
    }

    private void setValueToInternalMark(String userId, String subjectId, InternalMarkDTO internalMarkDTO, InternalMark internalMark) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(internalMark::setUser);
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        subject.ifPresent(internalMark::setSubject);
        internalMark.setMark(internalMarkDTO.getMark());
    }

    private void validationForSemesterAndSubject(String semesterId, String subjectId) {
        Optional<Subject> subject = subjectRepository.findByIdAndSemesterId(subjectId, semesterId);
        if(ObjectUtils.isEmpty(subject)){
            throw new CustomException(SUBJECT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    public void validationForSemester(String semesterId) {
        Optional<Semester> semester = semesterRepository.findById(semesterId);
        if(ObjectUtils.isEmpty(semester)){
            throw new CustomException(SEMESTER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }
}
