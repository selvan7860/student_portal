package com.example.student_portal.service;


import com.example.student_portal.dao.User;
import com.example.student_portal.dao.UserDetail;
import com.example.student_portal.dto.UserDetailDTO;
import com.example.student_portal.exception.CustomException;
import com.example.student_portal.repository.UserDetailRepository;
import com.example.student_portal.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService {

    private static final String USER_IS_INVALID = "User not found";
    private static final String USER_ALREADY_EXISTS = "User already exists";
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public UserDetailService(UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }


    public UserDetailDTO addUserDetail(UserDetailDTO userDetailDTO) {
        String userId = userDetailDTO.getUserId();
        alreadyUserExists(userId);
        validationForUser(userId);
        UserDetail userDetail = new UserDetail();
        userDetail.setFatherName(userDetailDTO.getFatherName());
        userDetail.setMotherName(userDetailDTO.getMotherName());
        userDetail.setFatherOccupation(userDetailDTO.getFatherOccupation());
        userDetail.setAnnualIncome(userDetailDTO.getAnnualIncome());
        userDetail.setMotherOccupation(userDetailDTO.getMotherOccupation());
        userDetail.setFatherPhoneNo(userDetailDTO.getFatherPhoneNo());
        userDetail.setMotherPhoneNo(userDetailDTO.getMotherPhoneNo());
        userDetail.setPhoneNo(userDetailDTO.getPhoneNo());
        userDetail.setDepartment(userDetailDTO.getDepartment());
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(userDetail::setUser);
        UserDetail saveUserDetails = userDetailRepository.save(userDetail);
        return convertDTO(saveUserDetails);
    }

    public UserDetailDTO updateUserDetail(String id, UserDetailDTO userDetailDTO) {
        validationForUser(id);
        UserDetail userDetail = userDetailRepository.findByUserId(id);
        setValueUserDetails(userDetail, userDetailDTO);
        UserDetail saveUpdateUserDetails = userDetailRepository.save(userDetail);
        return convertDTO(saveUpdateUserDetails);
    }

    private void setValueUserDetails(UserDetail userDetail, UserDetailDTO userDetailDTO) {
        userDetail.setFatherName(userDetailDTO.getFatherName());
        userDetail.setMotherName(userDetailDTO.getMotherName());
        userDetail.setFatherOccupation(userDetailDTO.getFatherOccupation());
        userDetail.setMotherOccupation(userDetailDTO.getMotherOccupation());
        userDetail.setFatherPhoneNo(userDetailDTO.getFatherPhoneNo());
        userDetail.setMotherPhoneNo(userDetailDTO.getMotherPhoneNo());
        userDetail.setAnnualIncome(userDetailDTO.getAnnualIncome());
        userDetail.setPhoneNo(userDetailDTO.getPhoneNo());
        userDetail.setDepartment(userDetailDTO.getDepartment());
    }

    private void alreadyUserExists(String userId) {
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        if(!ObjectUtils.isEmpty(userDetail)){
            throw new CustomException(USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }
    }

    private void validationForUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(ObjectUtils.isEmpty(user)){
            throw new CustomException(USER_IS_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    private UserDetailDTO convertDTO(UserDetail saveUserDetails) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(saveUserDetails.getId());
        userDetailDTO.setFatherName(saveUserDetails.getFatherName());
        userDetailDTO.setMotherName(saveUserDetails.getMotherName());
        userDetailDTO.setFatherOccupation(saveUserDetails.getFatherOccupation());
        userDetailDTO.setMotherOccupation(saveUserDetails.getMotherOccupation());
        userDetailDTO.setAnnualIncome(saveUserDetails.getAnnualIncome());
        userDetailDTO.setFatherPhoneNo(saveUserDetails.getFatherPhoneNo());
        userDetailDTO.setMotherPhoneNo(saveUserDetails.getMotherPhoneNo());
        userDetailDTO.setPhoneNo(saveUserDetails.getPhoneNo());
        userDetailDTO.setDepartment(saveUserDetails.getDepartment());
        userDetailDTO.setUserId(saveUserDetails.getUser().getId());
        return userDetailDTO;
    }


}
