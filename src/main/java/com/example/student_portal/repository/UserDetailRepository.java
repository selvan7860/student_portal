package com.example.student_portal.repository;

import com.example.student_portal.dao.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
    UserDetail findByUserId(String userId);
}
